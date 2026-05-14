package Lezione9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//  Si occupa esclusivamente delle operazioni sul database che riguardano l'accesso ai dati degli utenti.
//  Utilizzando i PreparedStatement, deve implementare l'inserimento di un nuovo utente gestendo il doppio salvataggio:
//  i dati comuni nella tabella "utenti" e quelli specifici nelle tabelle "studenti" o "docenti" .
//  Fornisce anche i metodi per la ricerca tramite ID o email e la stampa della lista completa .  

//La classe UtenteDAO deve gestire le operazioni sul database relative agli utenti.

//Metodi richiesti:
//1.public void inserisciUtente(Utente utente);
//2.public Utente cercaUtentePerId(int idUtente);
//3.public Utente cercaUtentePerEmail(String email);
//4.public void stampaTuttiGliUtenti();

//Quando viene inserito uno studente, il programma deve salvare i dati sia nella tabella utenti,
//sia nella tabella studenti.
//Quando viene inserito un docente, il programma deve salvare i dati sia nella tabella utenti, sia
//nella tabella docenti.

public class UtenteDAO { 

    // Il metodo inserisciUtente() gestisce l'inserimento di un nuovo utente nel database, 
    // assicurandosi di salvare i dati sia nella tabella "utenti" che in quella specifica per studenti o docenti.
    public void inserisciUtente(Utente utente) {
        if (utente == null) {
            System.out.println("Utente non valido.");
            return;
        }

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            conn.setAutoCommit(false);

            String sqlUtente = "INSERT INTO utenti (nome, cognome, email, tipo_utente) VALUES (?, ?, ?, ?)";
            int idUtente;
            try (PreparedStatement stmt = conn.prepareStatement(sqlUtente, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, utente.getNome());
                stmt.setString(2, utente.getCognome());
                stmt.setString(3, utente.getEmail());
                stmt.setString(4, utente.getTipoUtente());
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (!rs.next()) {
                        conn.rollback();
                        System.out.println("Impossibile recuperare l'ID utente.");
                        return;
                    }
                    idUtente = rs.getInt(1);
                }
            }

            if (utente instanceof Studente) {
                String sqlStudente = "INSERT INTO studenti (id_utente, classe) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlStudente)) {
                    stmt.setInt(1, idUtente);
                    stmt.setString(2, ((Studente) utente).getClasse());
                    stmt.executeUpdate();
                }
            } else if (utente instanceof Docente) {
                String sqlDocente = "INSERT INTO docenti (id_utente, materia) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlDocente)) {
                    stmt.setInt(1, idUtente);
                    stmt.setString(2, ((Docente) utente).getMateria());
                    stmt.executeUpdate();
                }
            }

            conn.commit();
            utente.setIdUtente(idUtente);
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Il metodo cercaUtentePerId() consente di cercare un utente specifico utilizzando il suo ID, restituendo un oggetto Utente se trovato.
    public Utente cercaUtentePerId(int idUtente) {
        String sql = "SELECT u.id_utente, u.nome, u.cognome, u.email, u.tipo_utente, s.classe, d.materia " +
                "FROM utenti u " +
                "LEFT JOIN studenti s ON u.id_utente = s.id_utente " +
                "LEFT JOIN docenti d ON u.id_utente = d.id_utente " +
                "WHERE u.id_utente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return null;
            }
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapUtente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Il metodo cercaUtentePerEmail() permette di cercare un utente tramite la sua email, restituendo un oggetto Utente se trovato.
    public Utente cercaUtentePerEmail(String email) {
        String sql = "SELECT u.id_utente, u.nome, u.cognome, u.email, u.tipo_utente, s.classe, d.materia " +
                "FROM utenti u " +
                "LEFT JOIN studenti s ON u.id_utente = s.id_utente " +
                "LEFT JOIN docenti d ON u.id_utente = d.id_utente " +
                "WHERE u.email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return null;
            }
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapUtente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Il metodo stampaTuttiGliUtenti() recupera e stampa la lista completa degli utenti presenti nel database, 
    // mostrando sia i dati comuni che quelli specifici per studenti e docenti.
    public void stampaTuttiGliUtenti() {
        String sql = "SELECT u.id_utente, u.nome, u.cognome, u.email, u.tipo_utente, s.classe, d.materia " +
                "FROM utenti u " +
                "LEFT JOIN studenti s ON u.id_utente = s.id_utente " +
                "LEFT JOIN docenti d ON u.id_utente = d.id_utente " +
                "ORDER BY u.id_utente";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || stmt == null) {
                System.out.println("Connessione al database non disponibile.");
                return;
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Utente utente = mapUtente(rs);
                    if (utente != null) {
                        System.out.println(formatUtente(utente));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  

    private Utente mapUtente(ResultSet rs) throws SQLException {
        int idUtente = rs.getInt("id_utente");
        String nome = rs.getString("nome");
        String cognome = rs.getString("cognome");
        String email = rs.getString("email");
        String tipo = rs.getString("tipo_utente");
        String classe = rs.getString("classe");
        String materia = rs.getString("materia");

        if ("STUDENTE".equalsIgnoreCase(tipo) || (tipo == null && classe != null)) {
            return new Studente(idUtente, nome, cognome, email, classe);
        }
        if ("DOCENTE".equalsIgnoreCase(tipo) || (tipo == null && materia != null)) {
            return new Docente(idUtente, nome, cognome, email, materia);
        }
        return null;
    }

    private String formatUtente(Utente utente) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(utente.getIdUtente())
                .append(" | ").append(utente.getNomeCompleto())
                .append(" | ").append(utente.getEmail())
                .append(" | ").append(utente.getTipoUtente());

        if (utente instanceof Studente) {
            sb.append(" | Classe: ").append(((Studente) utente).getClasse());
        } else if (utente instanceof Docente) {
            sb.append(" | Materia: ").append(((Docente) utente).getMateria());
        }
        return sb.toString();
    }
}
