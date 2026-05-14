// Questa classe ha il compito esclusivo di gestire la comunicazione JDBC con MySQL. Deve contenere un singolo metodo statico 
// incaricato di stabilire la connessione al database "biblioteca_scolastica" e restituire un oggetto di tipo Connection . 
// il Mysql server è localhost, la porta è 3306
// le credenziali sono nel file .env (MYSQL_USER e MYSQL_PASSWORD) .
// La classe DatabaseConnection deve essere responsabile della connessione al database.

package Lezione9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnection {
    // Il metodo getConnection() stabilisce la connessione al database e restituisce un oggetto Connection.
    public static Connection getConnection() {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");

        if (isBlank(url) || isBlank(user) || isBlank(password)) {
            Map<String, String> env = readEnvFile();
            if (isBlank(url)) {
                url = env.get("DB_URL");
            }
            if (isBlank(user)) {
                user = env.get("DB_USER");
            }
            if (isBlank(password)) {
                password = env.get("DB_PASS");
            }
        }

        if (isBlank(url)) {
            url = "jdbc:mysql://localhost:3306/biblioteca_scolastica";
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static Map<String, String> readEnvFile() {
        Map<String, String> env = new HashMap<>();
        List<Path> candidates = Arrays.asList(Paths.get(".env"), Paths.get("Lezione9", ".env"));
        for (Path candidate : candidates) {
            if (!Files.exists(candidate)) {
                continue;
            }
            try {
                for (String line : Files.readAllLines(candidate)) {
                    String trimmed = line.trim();
                    if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                        continue;
                    }
                    int eqIndex = trimmed.indexOf('=');
                    if (eqIndex <= 0) {
                        continue;
                    }
                    String key = trimmed.substring(0, eqIndex).trim();
                    String value = trimmed.substring(eqIndex + 1).trim();
                    env.put(key, value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return env;
    }
}
