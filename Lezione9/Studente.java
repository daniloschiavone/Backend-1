package Lezione9;

//Questa classe deve ereditare dalla superclasse Utente. 
//Aggiunge l'attributo specifico e privato "classe", corredato dai suoi getter e setter . 
//Il suo compito principale è fornire l'implementazione concreta dei metodi astratti ereditati: getNumeroMassimoPrestiti() 
//deve restituire il valore intero 3, mentre getTipoUtente() deve restituire la stringa "STUDENTE" .  
//La classe Studente deve estendere Utente.

//Attributo specifico:
//classe
//Metodi richiesti:
//getters e setters
//getNumeroMassimoPrestiti()
//getTipoUtente()
//Regole:
//1. getNumeroMassimoPrestiti() deve restituire 3
//2. getTipoUtente() deve restituire STUDENTE

public class Studente extends Utente {
    private String classe;

    public Studente(int idUtente, String nome, String cognome, String email, String classe) {
        super(idUtente, nome, cognome, email);
        this.classe = classe;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public int getNumeroMassimoPrestiti() {
        return 3;
    }

    @Override
    public String getTipoUtente() {
        return "STUDENTE";
    }
}
