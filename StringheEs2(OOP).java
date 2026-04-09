class StringheEs2OOP {
    // salutare uno studente
    public static void main(String[] args) {
    
        String nome = "Mario";
        String cognome = "Rossi";
        int eta = 20;
        String genere = "Maschio";

        salutaStudente(nome, cognome, eta, genere);
    }

    public static void salutaStudente(String nome, String cognome, int eta, String genere) {
        System.out.println("Ciao " + nome + " " + cognome + ", hai " + eta + " anni e sei un " + genere);
    }

}
