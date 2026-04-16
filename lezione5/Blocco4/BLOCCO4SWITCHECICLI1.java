//Usare switch per i giorni della settimana

import java.util.Scanner;

public class BLOCCO4SWITCHECICLI1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci un numero da 1 a 7 per sapere il giorno della settimana:");
        int giorno = input.nextInt();

        switch (giorno) {
            case 1:
                System.out.println("Lunedì");
                break;
            case 2:
                System.out.println("Martedì");
                break;
            case 3:
                System.out.println("Mercoledì");
                break;
            case 4:
                System.out.println("Giovedì");
                break;
            case 5:
                System.out.println("Venerdì");
                break;
            case 6:
                System.out.println("Sabato");
                break;
            case 7:
                System.out.println("Domenica");
                break;
            default:
                System.out.println("Numero non valido. Inserisci un numero da 1 a 7.");
        }

        input.close();
    }
    
}
