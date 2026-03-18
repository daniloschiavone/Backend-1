

import java.util.Scanner;

public class DivisioneSemplice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Inserisci il primo numero: ");
        double n1 = sc.nextDouble(); 

        System.out.print("Inserisci il secondo numero: ");
        double n2 = sc.nextDouble();

        double risultato = n1 / n2;

        System.out.println("Il risultato è: " + risultato);
        
        sc.close();
    }
}