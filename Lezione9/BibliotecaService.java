package Lezione9;

//  È il core logico dell'applicazione, dove i DAO vengono richiamati ma senza contenere query SQL dirette .
//  Contiene le funzioni di registrazione per utenti e libri . 
//  Coordina le regole rigide del metodo creaPrestito, dove deve validare
//  l'esistenza e la disponibilità delle entità e usare la chiamata polimorfica 
//  (es. utente.getNumeroMassimoPrestiti()) per bloccare le richieste oltre il limite consentito . 
//  Amministra la restituzione del libro richiamando i DAO per aggiornare le date e ripristinare la disponibilità del volume .

//La classe BibliotecaService deve contenere la logica principale del programma.
//I DAO devono occuparsi solo delle query al database.
//La classe BibliotecaService deve occuparsi dei controlli logici.
//Metodi richiesti:
//1. public void registraStudente(String nome, String cognome, String email, String classe);
//2. public void registraDocente(String nome, String cognome, String email, String materia);
//3. public void registraLibro(String titolo, String autore, int annoPubblicazione);
//4. public void creaPrestito(int idUtente, int idLibro);
//5. public void restituisciLibro(int idPrestito);

public class BibliotecaService {
    private final UtenteDAO utenteDAO;
    private final LibroDAO libroDAO;
    private final PrestitoDAO prestitoDAO;

    public BibliotecaService() {
        this.utenteDAO = new UtenteDAO();
        this.libroDAO = new LibroDAO();
        this.prestitoDAO = new PrestitoDAO();
    }

    // Il metodo registraStudente() gestisce la registrazione di un nuovo studente, 
    // assicurandosi di salvare tutte le informazioni necessarie nel database tramite il DAO appropriato.
    public void registraStudente(String nome, String cognome, String email, String classe) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email non valida.");
            return;
        }
        if (utenteDAO.cercaUtentePerEmail(email) != null) {
            System.out.println("Email gia registrata.");
            return;
        }
        Studente studente = new Studente(0, nome, cognome, email, classe);
        utenteDAO.inserisciUtente(studente);
        System.out.println("Studente registrato.");
    }

    // Il metodo registraDocente() gestisce la registrazione di un nuovo docente, 
    // assicurandosi di salvare tutte le informazioni necessarie nel database tramite il DAO appropriato.
    public void registraDocente(String nome, String cognome, String email, String materia) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email non valida.");
            return;
        }
        if (utenteDAO.cercaUtentePerEmail(email) != null) {
            System.out.println("Email gia registrata.");
            return;
        }
        Docente docente = new Docente(0, nome, cognome, email, materia);
        utenteDAO.inserisciUtente(docente);
        System.out.println("Docente registrato.");
    }

    // Il metodo registraLibro() gestisce l'inserimento di un nuovo libro nella biblioteca, 
    // assicurandosi di salvare tutte le informazioni necessarie nel database tramite il DAO appropriato.
    public void registraLibro(String titolo, String autore, int annoPubblicazione) {
        Libro libro = new Libro(0, titolo, autore, annoPubblicazione);
        libroDAO.inserisciLibro(libro);
        System.out.println("Libro registrato.");
    }

    // Il metodo creaPrestito() coordina la creazione di un nuovo prestito, 
    // validando l'esistenza e la disponibilità dell'utente e del libro, e utilizzando i DAO per aggiornare lo stato del prestito.
    public void creaPrestito(int idUtente, int idLibro) {
        Utente utente = utenteDAO.cercaUtentePerId(idUtente);
        if (utente == null) {
            System.out.println("Utente non trovato.");
            return;
        }

        Libro libro = libroDAO.cercaLibroPerId(idLibro);
        if (libro == null) {
            System.out.println("Libro non trovato.");
            return;
        }

        if (!libro.isDisponibile() || prestitoDAO.libroHaPrestitoAttivo(idLibro)) {
            System.out.println("Libro non disponibile per il prestito.");
            return;
        }

        int prestitiAttivi = prestitoDAO.contaPrestitiAttiviUtente(idUtente);
        if (prestitiAttivi >= utente.getNumeroMassimoPrestiti()) {
            System.out.println("Limite massimo di prestiti raggiunto.");
            return;
        }

        prestitoDAO.inserisciPrestito(idUtente, idLibro);
        libroDAO.aggiornaDisponibilita(idLibro, false);
        System.out.println("Prestito creato.");
    }

    // Il metodo restituisciLibro() gestisce la restituzione di un libro, 
    // aggiornando le date e ripristinando la disponibilità del volume tramite i DAO appropriati.
    public void restituisciLibro(int idPrestito) {
        Integer idLibro = prestitoDAO.cercaIdLibroDaPrestito(idPrestito);
        if (idLibro == null) {
            System.out.println("Prestito non trovato.");
            return;
        }
        if (!prestitoDAO.prestitoAttivo(idPrestito)) {
            System.out.println("Prestito gia restituito.");
            return;
        }
        prestitoDAO.restituisciLibro(idPrestito);
        libroDAO.aggiornaDisponibilita(idLibro, true);
        System.out.println("Libro restituito.");
    }

    public void stampaTuttiGliUtenti() {
        utenteDAO.stampaTuttiGliUtenti();
    }

    public void stampaTuttiILibri() {
        libroDAO.stampaTuttiILibri();
    }

    public void stampaLibriDisponibili() {
        libroDAO.stampaLibriDisponibili();
    }

    public void stampaPrestitiAttivi() {
        prestitoDAO.stampaPrestitiAttivi();
    }

    public void stampaStoricoPrestitiUtente(int idUtente) {
        prestitoDAO.stampaStoricoPrestitiUtente(idUtente);
    }
    
}
