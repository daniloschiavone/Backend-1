//Da file dato studenti_record.csv, creare un programma che legga i dati e si connetta ad un database, trovi la tabella studenti e inserendo i dati letti dal file. usando UPDATE, se lo studente è già presente, altrimenti usando INSERT.
//Usiano prepared statement per evitare problemi di SQL injection e per gestire meglio i dati dinamici. Il programma dovrebbe anche gestire eventuali errori di connessione o di inserimento dati, e stampare un messaggio di successo o di errore a seconda del risultato dell'operazione.
//prima di connettersi al database, il programma dovrebbe chiedere all'utente di inserire la password in modo sicuro (senza mostrarla a schermo).
//Dopo aver completato l'inserimento dei dati, il programma dovrebbe chiudere correttamente la connessione al database.

package Lezione8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Dafileadb {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/scuola"; 
        String user = "root";

        // 1.  password in modo sicuro
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci la password di MySQL: ");
        String password = scanner.nextLine();
        scanner.close();

        String percorsoFile = "Lezione8/studenti_record.csv";

        try {
            System.out.println("Connessione al database in corso...");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connessione stabilita!\n");

            // 3.  query (PreparedStatement previene SQL Injection)
            String checkSql = "SELECT id_studente FROM studenti WHERE nome = ? AND cognome = ?";
            String insertSql = "INSERT INTO studenti (nome, cognome, data_nascita) VALUES (?, ?, ?)";
            String updateSql = "UPDATE studenti SET data_nascita = ? WHERE id_studente = ?";

            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);

            // 4. Apriamo e leggiamo il file CSV
            BufferedReader br = new BufferedReader(new FileReader(percorsoFile));
            String riga;

            // SALTIAMO LA PRIMA RIGA (Intestazione: Nome,Cognome,Data_nascita)
            br.readLine(); 

            System.out.println("--- INIZIO ELABORAZIONE FILE ---");
            
            // Leggiamo riga per riga dal file finché non finiscono
            while ((riga = br.readLine()) != null) {
                String[] dati = riga.split(",");
                
                if (dati.length == 3) {
                    // Puliamo eventuali spazi vuoti
                    String nome = dati[0].trim();
                    String cognome = dati[1].trim();
                    String dataNascita = dati[2].trim();

                    // Controlliamo se lo studente è già nel DB
                    checkStmt.setString(1, nome);
                    checkStmt.setString(2, cognome);
                    checkStmt.setDate(3, java.sql.Date.valueOf(dataNascita));
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        // ESISTE GIÀ -> UPDATE
                        int id = rs.getInt("id_studente");
                        updateStmt.setDate(1, java.sql.Date.valueOf(dataNascita));
                        updateStmt.setInt(2, id);
                        updateStmt.executeUpdate();
                        System.out.println(" AGGIORNATO: " + nome + " " + cognome);
                    } else {
                        // NON ESISTE -> INSERT
                        insertStmt.setString(1, nome);
                        insertStmt.setString(2, cognome);
                        insertStmt.setDate (3, java.sql.Date.valueOf(dataNascita));
                        insertStmt.executeUpdate();
                        System.out.println("  INSERITO: " + nome + " " + cognome);
                    }
                    rs.close(); 
                }
            }

            // 5. Chiudiamo tutto
            br.close();
            checkStmt.close();
            insertStmt.close();
            updateStmt.close();
            conn.close();
            
            System.out.println("--- FINE ELABORAZIONE ---");
            System.out.println("Operazione completata! Database aggiornato e connessione chiusa.");

        } catch (Exception e) {
            System.out.println("\nERRORE IMPREVISTO:");
            System.out.println(e.getMessage());
        }
    }
}