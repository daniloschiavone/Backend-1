

import java.util.Scanner; 

public class EsempioSomma { 

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Inserisci il primo numero intero: ");
        int numero1 = sc.nextInt();
        
        System.out.println("Inserisci il secondo numero intero: "); 
        int numero2 = sc.nextInt();
        
        int somma = numero1 + numero2; 
        
        System.out.println("Ecco il risultato della somma: " + somma);
        
        sc.close(); 
    }
}