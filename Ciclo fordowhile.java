import java.util.Scanner;

class CicloFordowhile {
    // scrivere un programma che:
    // 1. chiede all'utente di inserire un N 
    // 2. stampa i numeri primi da 
    // 3. conta quanti sono
    // 4. stampa il più grande  
    // 5. stampa la somma
    public static void main(String[] args) {
    int N;
    Scanner scanner = new Scanner(System.in);
        int count = 0;
        int maxPrime = 0;
        int sum = 0;

    System.out.print("Inserisci un numero N: ");
    N = scanner.nextInt();  
        
        System.out.println("Numeri primi da 1 a " + N + ":");
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                count++;
                sum += i;
                if (i > maxPrime) {
                    maxPrime = i;
                }
            }
        }

        System.out.println("\n\nTotale numeri primi: " + count);
        System.out.println("Il più grande numero primo: " + maxPrime);
        System.out.println("La somma dei numeri primi: " + sum);

        scanner.close();
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
