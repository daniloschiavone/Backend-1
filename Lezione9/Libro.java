package Lezione9;

// Funge da modello dati per i libri della biblioteca. 
// Richiede gli attributi privati idLibro, titolo, autore, annoPubblicazione e un booleano "disponibile" per tracciare
// lo stato del prestito . Oltre ai classici metodi getter e setter, 
// deve includere un metodo toString() per formattare correttamente i dati dell'oggetto durante le stampe.
//La classe Libro deve rappresentare un libro della biblioteca.

//Attributi richiesti:
//1. idLibro
//2. titolo
//3. autore
//4. annoPubblicazione
//5. disponibile

//Metodi richiesti:
//1. getters
//2. setters
//3. toString()


public class Libro {

        private int idLibro;
        private String titolo;
        private String autore;
        private int annoPubblicazione;
        private boolean disponibile;
    
        public Libro(int idLibro, String titolo, String autore, int annoPubblicazione) {
            this.idLibro = idLibro;
            this.titolo = titolo;
            this.autore = autore;
            this.annoPubblicazione = annoPubblicazione;
            this.disponibile = true; // I libri sono disponibili di default quando vengono creati
        }
    
        public int getIdLibro() {
            return idLibro;
        }
    
        public void setIdLibro(int idLibro) {
            this.idLibro = idLibro;
        }
    
        public String getTitolo() {
            return titolo;
        }
    
        public void setTitolo(String titolo) {
            this.titolo = titolo;
        }
    
        public String getAutore() {
            return autore;
        }
    
        public void setAutore(String autore) {
            this.autore = autore;
        }
    
        public int getAnnoPubblicazione() {
            return annoPubblicazione;
        }
    
        public void setAnnoPubblicazione(int annoPubblicazione) {
            this.annoPubblicazione = annoPubblicazione;
        }
    
        public boolean isDisponibile() {
            return disponibile;
        }
    
        public void setDisponibile(boolean disponibile) {
            this.disponibile = disponibile;
        }
    
        @Override
        public String toString() {
                return "ID: " + idLibro +
                    " | " + titolo +
                    " | " + autore +
                    " | " + annoPubblicazione +
                    " | Disponibile: " + disponibile;
        }
}

