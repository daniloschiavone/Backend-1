import java.util.Scanner;

public class PariDispari {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Inserisci un numero intero: ");
        int numero = sc.nextInt();

        
        if (numero % 2 == 0) {
            System.out.println("Il numero " + numero + " è PARI");
        } else {
            System.out.println("Il numero " + numero + " è DISPARI");
        }

        sc.close();
    }
}