package lezione7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

            // 1. Prepariamo la query con i punti interrogativi (?)
            // id_studente non c'è perché è AUTO_INCREMENT
            String sql = "INSERT INTO studenti (nome, cognome, data_nascita) VALUES (?, ?, ?)";
            
            // Creiamo il PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // --- INSERIMENTO STUDENTE 1 ---
            pstmt.setString(1, "Edoardo");     // Sostituisce il 1° punto interrogativo
            pstmt.setString(2, "Gialli");      // Sostituisce il 2° punto interrogativo
            pstmt.setString(3, "2005-04-12");  // Sostituisce il 3° punto interrogativo
            pstmt.executeUpdate();             // Invia il comando al database
            System.out.println("Inserito: Edoardo Gialli");

            // --- INSERIMENTO STUDENTE 2 ---
            // Riutilizziamo lo stesso pstmt, cambiamo solo i dati!
            pstmt.setString(1, "Sara");
            pstmt.setString(2, "Marrone");
            pstmt.setString(3, "2006-08-25");
            pstmt.executeUpdate();
            System.out.println("Inserito: Sara Marrone");

            // --- INSERIMENTO STUDENTE 3 ---
            pstmt.setString(1, "Luigi");
            pstmt.setString(2, "Viola");
            pstmt.setString(3, "2004-12-01");
            pstmt.executeUpdate();
            System.out.println("Inserito: Luigi Viola");

            // Chiudiamo i collegamenti
            pstmt.close();
            conn.close();

            System.out.println("\n✅ Tutti gli studenti sono stati aggiunti con successo!");

        } catch (Exception e) {
            System.out.println("\n❌ ERRORE DURANTE L'INSERIMENTO!");
            System.out.println(e.getMessage());
        }
    }
}