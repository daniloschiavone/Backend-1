package Lezione9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Gestisce unicamente le query relative alla tabella dei libri. 
// Include i metodi PreparedStatement per inserire un nuovo libro,
// cercarlo tramite il suo ID, stampare l'elenco completo o filtrare solo i libri attualmente disponibili . 
// Deve fornire un metodo per aggiornare il valore booleano della disponibilità di un singolo libro nel database.

//La classe LibroDAO deve gestire le operazioni sul database relative ai libri.
//Metodi richiesti:
//1. public void inserisciLibro(Libro libro);
//2. public Libro cercaLibroPerId(int idLibro);
//3. public void stampaTuttiILibri();
//4. public void stampaLibriDisponibili();
//5. public void aggiornaDisponibilita(int idLibro, boolean disponibile);

public class LibroDAO { 

    // Il metodo inserisciLibro() gestisce l'inserimento di un nuovo libro nel database, 
    // assicurandosi di salvare tutti i dati necessari nella tabella "libri".
    public void inserisciLibro(Libro libro) {
        if (libro == null) {
            System.out.println("Libro non valido.");
            return;
        }

        String sql = "INSERT INTO libri (titolo, autore, anno_pubblicazione, disponibile) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            stmt.setString(1, libro.getTitolo());
            stmt.setString(2, libro.getAutore());
            stmt.setInt(3, libro.getAnnoPubblicazione());
            stmt.setBoolean(4, libro.isDisponibile());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Il metodo cercaLibroPerId() consente di cercare un libro specifico utilizzando il suo ID, restituendo un oggetto Libro se trovato.
    public Libro cercaLibroPerId(int idLibro) {
        String sql = "SELECT id_libro, titolo, autore, anno_pubblicazione, disponibile FROM libri WHERE id_libro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return null;
            }
            stmt.setInt(1, idLibro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Libro libro = new Libro(
                            rs.getInt("id_libro"),
                            rs.getString("titolo"),
                            rs.getString("autore"),
                            rs.getInt("anno_pubblicazione")
                    );
                    libro.setDisponibile(rs.getBoolean("disponibile"));
                    return libro;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Il metodo stampaTuttiILibri() recupera e stampa la lista completa dei libri presenti nel database, 
    // mostrando tutte le informazioni rilevanti per ciascun libro.
    public void stampaTuttiILibri() {
        String sql = "SELECT id_libro, titolo, autore, anno_pubblicazione, disponibile FROM libri ORDER BY id_libro";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Libro libro = new Libro(
                            rs.getInt("id_libro"),
                            rs.getString("titolo"),
                            rs.getString("autore"),
                            rs.getInt("anno_pubblicazione")
                    );
                    libro.setDisponibile(rs.getBoolean("disponibile"));
                    System.out.println(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  

    // Il metodo stampaLibriDisponibili() filtra e stampa solo i libri attualmente disponibili, 
    // facilitando la consultazione per gli utenti interessati a prendere in prestito un libro.
    public void stampaLibriDisponibili() {
        String sql = "SELECT id_libro, titolo, autore, anno_pubblicazione, disponibile FROM libri WHERE disponibile = true";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Libro libro = new Libro(
                            rs.getInt("id_libro"),
                            rs.getString("titolo"),
                            rs.getString("autore"),
                            rs.getInt("anno_pubblicazione")
                    );
                    libro.setDisponibile(rs.getBoolean("disponibile"));
                    System.out.println(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    // Il metodo aggiornaDisponibilita() consente di modificare lo stato di disponibilità di un libro specifico nel database, 
    // permettendo così di tenere traccia dei prestiti e delle restituzioni in modo accurato.
    public void aggiornaDisponibilita(int idLibro, boolean disponibile) {
        String sql = "UPDATE libri SET disponibile = ? WHERE id_libro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            stmt.setBoolean(1, disponibile);
            stmt.setInt(2, idLibro);
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                System.out.println("Nessun libro aggiornato. ID non trovato.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
