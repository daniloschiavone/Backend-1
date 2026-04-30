package lezione7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement; // Usiamo lo Statement classico
import java.util.Scanner;

public class InserisciStudenti {
    public static void main(String[] args) {
        // L'URL punta al database "scuola"
        String url = "jdbc:mysql://localhost:3306/scuola"; 
        String user = "root"; 

        // Chiediamo la password in modo sicuro all'avvio
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci la password di MySQL: ");
        String password = scanner.nextLine();
        scanner.close();

        try {
            System.out.println("Connessione al database 'scuola' in corso...");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connessione stabilita! Inizio l'inserimento...\n");

            // Creiamo lo Statement (una volta sola)
            Statement stmt = conn.createStatement();

            // --- INSERIMENTO STUDENTE 1 ---
            // Nota l'uso degli apici singoli ' per il testo e le date dentro la stringa!
            String sql1 = "INSERT INTO studenti (nome, cognome, data_nascita) VALUES ('Francesca', 'Russo', '2005-02-28')";
            stmt.executeUpdate(sql1);
            System.out.println("Inserito: Francesca Russo");

            // --- INSERIMENTO STUDENTE 2 ---
            String sql2 = "INSERT INTO studenti (nome, cognome, data_nascita) VALUES ('Matteo', 'Ferrari', '2006-07-15')";
            stmt.executeUpdate(sql2);
            System.out.println("Inserito: Matteo Ferrari");

            // --- INSERIMENTO STUDENTE 3 ---
            String sql3 = "INSERT INTO studenti (nome, cognome, data_nascita) VALUES ('Chiara', 'Esposito', '2004-11-03')";
            stmt.executeUpdate(sql3);
            System.out.println("Inserito: Chiara Esposito");
            
            // --- INSERIMENTO STUDENTE 4 ---
            String sql4 = "INSERT INTO studenti (nome, cognome, data_nascita) VALUES ('Lorenzo', 'Ricci', '2005-09-21')";
            stmt.executeUpdate(sql4);
            System.out.println("Inserito: Lorenzo Ricci");

            // Chiudiamo i collegamenti per liberare memoria
            stmt.close();
            conn.close();

            System.out.println("\n✅ Tutti i 4 nuovi studenti sono stati aggiunti con successo!");

        } catch (Exception e) {
            System.out.println("\n❌ ERRORE DURANTE L'INSERIMENTO!");
            System.out.println(e.getMessage());
        }
    }
}