
//Chiedere l’età e dire se una persona è maggiorenne

import java.util.Scanner;

public class BLOCCO3ESERCIZIBASElogicaif2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci l'età: ");
        int eta = scanner.nextInt();

        if (eta >= 18) {
            System.out.println("La persona è maggiorenne.");
        } else {
            System.out.println("La persona non è maggiorenne.");
        }

        scanner.close();
    }
}
