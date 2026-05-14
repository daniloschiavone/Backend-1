package Lezione9;

//  Questa è la classe base del sistema e deve essere obbligatoriamente astratta.
//  Al suo interno devi dichiarare gli attributi privati per idUtente, nome, cognome ed email,
//  applicando il principio dell'incapsulamento tramite i relativi metodi getter e setter .
//  Oltre al metodo concreto getNomeCompleto(), che restituisce la concatenazione di nome e cognome,
//  devi definire due metodi astratti fondamentali per il polimorfismo: 
//  getNumeroMassimoPrestiti() e getTipoUtente().

//  Attributi richiesti:
//  1. idUtente
//  2. nome
//  3. cognome
//  4. email
//  Metodi richiesti:
//  1. getters e setters
//  2. getNomeCompleto()
//  3. getNumeroMassimoPrestiti()
//  4. getTipoUtente()
//  Il metodo getNomeCompleto() deve restituire nome e cognome dell’utente.
//  I metodi getNumeroMassimoPrestiti() e getTipoUtente() devono essere astratti.


public abstract class Utente {
    private int idUtente;
    private String nome;
    private String cognome;
    private String email;

    public Utente(int idUtente, String nome, String cognome, String email) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nome + " " + cognome;
    }

    public abstract int getNumeroMassimoPrestiti();
    public abstract String getTipoUtente();
}
