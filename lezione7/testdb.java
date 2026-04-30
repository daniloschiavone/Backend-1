package lezione7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; // Importiamo lo Scanner

public class testdb {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/scuola"; 
        String user = "root"; 
        
        // 1. Chiediamo la password all'utente tramite terminale
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci la password per il database MySQL: ");
        String password = scanner.nextLine();
        scanner.close(); // Chiudiamo lo scanner

        try {
            System.out.println("Tentativo di connessione in corso...");
            
            // 2. Usiamo la password appena digitata
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connessione riuscita alla grande! 🎉");

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM studenti";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- Elenco Studenti Trovati ---");
            while (rs.next()) {
                int id = rs.getInt("id_studente");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                System.out.println("ID: " + id + " | " + nome + " " + cognome);
            }
            
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("\n❌ ERRORE: Password errata o database spento!");
            e.printStackTrace();
        }
    }
}