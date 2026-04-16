//Stampare i numeri da 1 a 100
import java.util.Scanner;


public class BLOCCO4SWITCHECICLI3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci un numero da 1 a 100:");
        int numero = input.nextInt();

        if (numero >= 1 && numero <= 100) {
            for (int i = 1; i <= numero; i++) {
                System.out.println(i);
            }
        } else {
            System.out.println("Numero non valido. Inserisci un numero da 1 a 100.");
        }

        input.close();
    }
    
}
