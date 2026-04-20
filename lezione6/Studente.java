package lezione6;

public class Studente {
    private final String matricola;
    private final String nome;
    private final String cognome;
    private final String classe;

    public Studente(String matricola, String nome, String cognome, String classe) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.classe = classe;
    }

    public String getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getClasse() {
        return classe;
    }

    // Metodo per restituire la riga nel formato corretto per il file (matricola;nome;cognome;classe)
    public String toFileLine() {
        return matricola + ";" + nome + ";" + cognome + ";" + classe;
    }

    @Override
    public String toString() {
        return toFileLine();
    }
}
