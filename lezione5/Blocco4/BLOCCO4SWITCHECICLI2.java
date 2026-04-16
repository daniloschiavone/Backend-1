//Creare una mini calcolatrice con switch
import java.util.Scanner;

public class BLOCCO4SWITCHECICLI2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il primo numero:");
        double num1 = input.nextDouble();
        System.out.println("Inserisci il secondo numero:");
        double num2 = input.nextDouble();
        System.out.println("Scegli l'operazione (+, -, *, /):");
        char operazione = input.next().charAt(0);

        switch (operazione) {
            case '+':
                System.out.println("Risultato: " + (num1 + num2));
                break;
            case '-':
                System.out.println("Risultato: " + (num1 - num2));
                break;
            case '*':
                System.out.println("Risultato: " + (num1 * num2));
                break;
            case '/':
                if (num2 != 0) {
                    System.out.println("Risultato: " + (num1 / num2));
                } else {
                    System.out.println("Errore: divisione per zero non consentita.");
                }
                break;
            default:
                System.out.println("Operazione non valida. Scegli tra +, -, *, /.");
        }

        input.close();
    }
    
}
