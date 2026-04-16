//Stampare i numeri pari da 1 a 50
import java.util.Scanner;

public class BLOCCO4SWITCHECICLI4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci un numero da 1 a 50:");
        int numero = input.nextInt();

        if (numero >= 1 && numero <= 50) {
            System.out.println("Numeri pari da 1 a " + numero + ":");
            for (int i = 1; i <= numero; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } else {
            System.out.println("Numero non valido. Inserisci un numero da 1 a 50.");
        }

        input.close();
    }
    
}
