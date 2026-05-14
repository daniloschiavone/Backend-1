package Lezione9;

// Rappresenta l'entità logica del prestito. Deve contenere gli attributi privati idPrestito,
// l'utente che ha effettuato la richiesta, il libro prestato, la dataPrestito, 
// la dataRestituzione e lo stato attuale (ATTIVO o RESTITUITO) .
// Sono richiesti i getter, i setter e il metodo toString() . 
//La classe Prestito deve rappresentare un prestito.

//Attributi richiesti:
//1. idPrestito
//2. utente
//3. libro
//4. dataPrestito
//5. dataRestituzione
//6. stato
//Metodi richiesti:
//1. getters
//2. setters
//3. toString()

public class Prestito {
    private int idPrestito;
    private int idUtente;
    private int idLibro;
    private String dataPrestito;
    private String dataRestituzione;
    private String stato; // Può essere "ATTIVO" o "RESTITUITO"

    public Prestito(int idPrestito, int idUtente, int idLibro, String dataPrestito) {
        this.idPrestito = idPrestito;
        this.idUtente = idUtente;
        this.idLibro = idLibro;
        this.dataPrestito = dataPrestito;
        this.stato = "ATTIVO"; // Il prestito è attivo di default quando viene creato
    }

    public int getIdPrestito() {
        return idPrestito;
    }

    public void setIdPrestito(int idPrestito) {
        this.idPrestito = idPrestito;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getDataPrestito() {
        return dataPrestito;
    }

    public void setDataPrestito(String dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public String getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataRestituzione(String dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
        this.stato = "RESTITUITO"; // Quando viene impostata la data di restituzione, il prestito diventa restituito
    }

    public String getStato() {
        return stato;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "idPrestito=" + idPrestito +
                ", idUtente=" + idUtente +
                ", idLibro=" + idLibro +
                ", dataPrestito='" + dataPrestito + '\'' +
                ", dataRestituzione='" + dataRestituzione + '\'' +
                ", stato='" + stato + '\'' +
                '}';
    }
        
}
