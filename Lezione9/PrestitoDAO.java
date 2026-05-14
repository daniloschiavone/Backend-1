package Lezione9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//  Contiene le query destinate alla tabella prestiti. 
//  Deve permettere l'inserimento di un nuovo record, l'aggiornamento dello stato in fase di restituzione,
//  il conteggio dei prestiti attualmente attivi per un dato utente e la verifica per assicurarsi che 
//  un determinato libro non abbia già un prestito attivo in corso .
//  Gestisce la stampa dei prestiti attivi e dello storico per ID utente . 

//La classe PrestitoDAO deve gestire le operazioni sul database relative ai prestiti.
//Metodi richiesti:
//1. public void inserisciPrestito(int idUtente, int idLibro);
//2. public void restituisciLibro(int idPrestito);
//3. public int contaPrestitiAttiviUtente(int idUtente);
//4. public boolean libroHaPrestitoAttivo(int idLibro);
//5. public void stampaPrestitiAttivi();
//6. public void stampaStoricoPrestitiUtente(int idUtente);

public class PrestitoDAO {
    // Il metodo inserisciPrestito() gestisce l'inserimento di un nuovo prestito nel database, 
    // assicurandosi di salvare tutti i dati necessari nella tabella "prestiti".
    public void inserisciPrestito(int idUtente, int idLibro) {
        String sql = "INSERT INTO prestiti (id_utente, id_libro, data_prestito, stato) VALUES (?, ?, CURRENT_DATE, 'ATTIVO')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            stmt.setInt(1, idUtente);
            stmt.setInt(2, idLibro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Il metodo restituisciLibro() consente di aggiornare lo stato di un prestito specifico in fase di restituzione, 
    // modificando il record corrispondente nel database per riflettere la restituzione del libro.
    public void restituisciLibro(int idPrestito) {
        String sql = "UPDATE prestiti SET data_restituzione = CURRENT_DATE, stato = 'RESTITUITO' WHERE id_prestito = ? AND stato = 'ATTIVO'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            stmt.setInt(1, idPrestito);
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                System.out.println("Prestito non trovato o gia restituito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Il metodo contaPrestitiAttiviUtente() permette di contare quanti prestiti attivi ha attualmente un dato utente, 
    // restituendo un intero che rappresenta il numero di prestiti attivi associati all'ID utente fornito.
    public int contaPrestitiAttiviUtente(int idUtente) {
        String sql = "SELECT COUNT(*) AS totale FROM prestiti WHERE id_utente = ? AND stato = 'ATTIVO'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return 0;
            }
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("totale");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Il metodo libroHaPrestitoAttivo() verifica se un determinato libro ha già un prestito attivo in corso, 
    // restituendo un booleano che indica se il libro è attualmente prestato o meno.
    public boolean libroHaPrestitoAttivo(int idLibro) {
        String sql = "SELECT COUNT(*) AS totale FROM prestiti WHERE id_libro = ? AND stato = 'ATTIVO'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return false;
            }
            stmt.setInt(1, idLibro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("totale") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Il metodo stampaPrestitiAttivi() recupera e stampa la lista completa dei prestiti attivi presenti nel database, 
    // mostrando tutte le informazioni rilevanti per ciascun prestito.
    public void stampaPrestitiAttivi() {
        String sql = "SELECT id_prestito, id_utente, id_libro, data_prestito, data_restituzione, stato FROM prestiti WHERE stato = 'ATTIVO' ORDER BY id_prestito";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(formatPrestito(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  

    // Il metodo stampaStoricoPrestitiUtente() filtra e stampa lo storico dei prestiti per un dato ID utente, 
    // facilitando la consultazione delle attività passate dell'utente in relazione ai prestiti effettuati.
    public void stampaStoricoPrestitiUtente(int idUtente) {
        String sql = "SELECT id_prestito, id_utente, id_libro, data_prestito, data_restituzione, stato FROM prestiti WHERE id_utente = ? ORDER BY data_prestito DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(formatPrestito(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer cercaIdLibroDaPrestito(int idPrestito) {
        String sql = "SELECT id_libro FROM prestiti WHERE id_prestito = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return null;
            }
            stmt.setInt(1, idPrestito);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_libro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean prestitoAttivo(int idPrestito) {
        String sql = "SELECT stato FROM prestiti WHERE id_prestito = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return false;
            }
            stmt.setInt(1, idPrestito);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String stato = rs.getString("stato");
                    return "ATTIVO".equalsIgnoreCase(stato);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String formatPrestito(ResultSet rs) throws SQLException {
        return "Prestito{" +
                "idPrestito=" + rs.getInt("id_prestito") +
                ", idUtente=" + rs.getInt("id_utente") +
                ", idLibro=" + rs.getInt("id_libro") +
                ", dataPrestito=" + rs.getDate("data_prestito") +
                ", dataRestituzione=" + rs.getDate("data_restituzione") +
                ", stato=" + rs.getString("stato") +
                "}";
    }
    
}
