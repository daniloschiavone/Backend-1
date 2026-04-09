import java.util.Scanner;

class pendolo {
    public static void main(String[] args) {
        int numeroSegreto = (int) (Math.random() * 100) + 1;
        int tentativi = 0;
        int numeroMaxTentativi = 10;
        int indovinato = 0;

        System.out.println("Benvenuto al gioco 'Indovina il Numero'!");
        System.out.println("Ho scelto un numero tra 1 e 100. Prova a indovinarlo!");

        Scanner scanner = new Scanner(System.in);
        while (indovinato == 0 && tentativi < numeroMaxTentativi) {
            System.out.print("Inserisci un numero: ");
            System.out.println("Tentativo " + (tentativi + 1) + " di " + numeroMaxTentativi);
            int tentativo = scanner.nextInt();
            tentativi++;

            int distanza = Math.abs(numeroSegreto - tentativo);
            if (tentativo < numeroSegreto) {
                if (distanza <= 5) {
                    System.out.println("E piu grande. Ci sei quasi!");
                } else {
                    System.out.println("Troppo basso! Riprova.");
                }
            } else if (tentativo > numeroSegreto) {
                if (distanza <= 5) {
                    System.out.println("E piu piccolo. Ci sei quasi!");
                } else {
                    System.out.println("Troppo alto! Riprova.");
                }
            } else {
                indovinato = 1;
                System.out.println("Congratulazioni! Hai indovinato il numero in " + tentativi + " tentativi.");
            }
        }

        if (indovinato == 0) {
            System.out.println("Hai perso! Hai finito i tentativi. Il numero era: " + numeroSegreto);
        }

        scanner.close();
    }
}