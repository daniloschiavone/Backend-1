
//Verificare se un numero è compreso tra 10 e 20

import java.util.Scanner;

public class BLOCCO3ESERCIZIBASElogicaif5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci un numero: ");
        int numero = scanner.nextInt();
        
        if (numero >= 10 && numero <= 20) {
            System.out.println("Il numero è compreso tra 10 e 20.");
        } else {
            System.out.println("Il numero non è compreso tra 10 e 20.");
        }
        scanner.close();
    }
}
