public class StringheEs1 {
    public static void main(String[] args) {
        String name = "Mario";

        // Stampa la lunghezza della stringa
        System.out.println("Lunghezza della stringa: " + name.length());
        // se è uguale a mario è true altrimenti è false
        System.out.println("La stringa è uguale a 'Mario': " + name.equals("Mario"));
        //stampa le prime 3 lettere della stringa
        System.out.println("Le prime 3 lettere della stringa: " + name.substring(0, 3));
        //stampa la stringa in maiuscolo
        System.out.println("Stringa in maiuscolo: " + name.toUpperCase());

    }
}