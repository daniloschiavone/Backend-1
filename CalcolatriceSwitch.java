import java.util.Scanner;

class CalcolatriceSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il primo numero: ");
        double num1 = scanner.nextDouble();

        System.out.print("Inserisci il secondo numero: ");
        double num2 = scanner.nextDouble();

        System.out.print("Scegli l'operazione (+, -, *, /): ");
        char operazione = scanner.next().charAt(0);

        double risultato;

        switch (operazione) {
            case '+':
                risultato = num1 + num2;
                System.out.println("Risultato: " + risultato);
                break;
            case '-':
                risultato = num1 - num2;
                System.out.println("Risultato: " + risultato);
                break;
            case '*':
                risultato = num1 * num2;
                System.out.println("Risultato: " + risultato);
                break;
            case '/':
                if (num2 != 0) {
                    risultato = num1 / num2;
                    System.out.println("Risultato: " + risultato);
                } else {
                    System.out.println("Errore: divisione per zero!");
                }
                break;
            default:
                System.out.println("Operazione non valida!");
        }

        scanner.close();
    }
}