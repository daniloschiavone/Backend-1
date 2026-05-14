package Lezione9;

//In modo speculare a Studente, questa classe estende Utente. 
//Introduce l'attributo privato "materia" con i rispettivi metodi di accesso . L'implementazione dei metodi astratti richiede 
//che getNumeroMassimoPrestiti() restituisca 5 e che getTipoUtente() restituisca "DOCENTE" .  
//La classe Docente deve estendere Utente.

//Attributo specifico:
//materia
//Metodi richiesti:
//1. getters e setters
//2. getNumeroMassimoPrestiti()
//3. getTipoUtente()
//Regole:
//1. getNumeroMassimoPrestiti() deve restituire 5
//2. getTipoUtente() deve restituire DOCENTE
public class Docente extends Utente {
    private String materia;

    public Docente(int idUtente, String nome, String cognome, String email, String materia) {
        super(idUtente, nome, cognome, email);
        this.materia = materia;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public int getNumeroMassimoPrestiti() {
        return 5;
    }

    @Override
    public String getTipoUtente() {
        return "DOCENTE";
    }
    
}
