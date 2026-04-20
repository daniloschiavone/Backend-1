//Inserire 5 numeri e calcolare la media

import java.util.Scanner;

public class ARRAYESTRINGHE1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] numeri = new double[5];
        double somma = 0;

        for (int i = 0; i < 5; i++) {
            System.out.println("Inserisci il " + (i + 1) + "° numero:");
            numeri[i] = input.nextDouble();
            somma += numeri[i];
        }

        double media = somma / 5;
        System.out.println("La media dei numeri inseriti è: " + media);

        input.close();
    }
}
