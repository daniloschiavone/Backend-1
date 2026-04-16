//Calcolare il fattoriale di un numero
import java.util.Scanner;

public class BLOCCO4SWITCHECICLI5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci un numero per calcolare il fattoriale:");
        int numero = input.nextInt();

        if (numero < 0) {
            System.out.println("Errore: il fattoriale non è definito per numeri negativi.");
        } else {
            long fattoriale = 1;
            for (int i = 1; i <= numero; i++) {
                fattoriale *= i;
            }
            System.out.println("Il fattoriale di " + numero + " è: " + fattoriale);
        }

        input.close();
    }
    
}
