# Risposte alle domande (Lezione 9)

1. Dove hai usato l'incapsulamento?
   - Nelle classi modello come `Utente`, `Studente`, `Docente`, `Libro` e `Prestito`: gli attributi sono `private` e l'accesso avviene tramite getter e setter.

2. Dove hai usato l'ereditarieta?
   - `Studente` e `Docente` estendono la classe astratta `Utente`.

3. Dove hai usato il polimorfismo?
   - Nel metodo `creaPrestito` di `BibliotecaService`, quando chiama `utente.getNumeroMassimoPrestiti()` su un riferimento di tipo `Utente`: il valore cambia in base al tipo reale (`Studente` o `Docente`).
   - Anche in `UtenteDAO`, quando gestisce un `Utente` che puo essere `Studente` o `Docente`.

4. A cosa serve la classe astratta Utente?
   - A raccogliere i dati comuni (id, nome, cognome, email) e a imporre i metodi astratti `getNumeroMassimoPrestiti()` e `getTipoUtente()`, che ogni sottoclasse deve implementare.

5. A cosa servono le classi DAO?
   - A separare la logica applicativa dalle query SQL. Ogni DAO si occupa solo di leggere/scrivere sul database per una specifica entita (`Utente`, `Libro`, `Prestito`).

6. Perche si usa PreparedStatement?
   - Per passare parametri in modo sicuro e tipizzato, evitare SQL injection e migliorare la gestione delle query riutilizzabili.

7. Come funziona la connessione al database?
   - `DatabaseConnection.getConnection()` legge le credenziali da variabili d'ambiente o dal file `.env`, usa il driver MySQL (`com.mysql.cj.jdbc.Driver`) e crea la connessione con `DriverManager.getConnection()`.

8. Cosa succede quando uno studente supera 3 prestiti?
   - `BibliotecaService.creaPrestito()` verifica i prestiti attivi. Se sono 3 o piu, stampa "Limite massimo di prestiti raggiunto." e non crea il prestito.

9. Cosa succede quando un docente supera 5 prestiti?
   - Stessa logica dello studente: se i prestiti attivi sono 5 o piu, il prestito non viene creato e viene mostrato il messaggio di limite raggiunto.

10. Cosa succede quando un libro viene restituito?
   - `PrestitoDAO.restituisciLibro()` aggiorna il prestito con `data_restituzione` e stato `RESTITUITO`, e `LibroDAO.aggiornaDisponibilita()` imposta il libro come disponibile.
