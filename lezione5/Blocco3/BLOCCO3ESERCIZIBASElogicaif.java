
//Scrivere un programma che verifica se un numero è pari o dispari

import java.util.Scanner;

public class BLOCCO3ESERCIZIBASElogicaif {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci un numero: ");
        int numero = scanner.nextInt();

        if (numero % 2 == 0) {
            System.out.println("Il numero è pari.");
        } else {
            System.out.println("Il numero è dispari.");
        }

        scanner.close();
    }
}
   