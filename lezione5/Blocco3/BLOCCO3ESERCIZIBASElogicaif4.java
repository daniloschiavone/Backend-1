
//Dato un voto, stampare: insufficiente, sufficiente, buono, ottimo

import java.util.Scanner;

public class BLOCCO3ESERCIZIBASElogicaif4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il voto: ");
        int voto = scanner.nextInt();

        if (voto < 6) {
            System.out.println("Insufficiente");
        } else if (voto < 8) {
            System.out.println("Sufficiente");
        } else if (voto < 10) {
            System.out.println("Buono");
        } else {
            System.out.println("Ottimo");
        }

        scanner.close();
    }
}
