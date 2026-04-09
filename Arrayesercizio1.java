//crea un programma in java che:
//generi un array di 100 numeri interi
//con numeri casuali tra 0 e 100
//separi i numeri pari da quelli dispari in due array distinti
//stampa l'array originale
//stampa l'array dei numeri pari
//stampa l'array dei numeri dispari

public class Arrayesercizio1 {
   public static void main(String[] args) {
      int[] originalArray = new int[100];
      int[] evenArray = new int[100];
      int[] oddArray = new int[100];
      int evenCount = 0;
      int oddCount = 0;

      // Genera numeri casuali e separa pari e dispari
      for (int i = 0; i < originalArray.length; i++) {
         originalArray[i] = (int) (Math.random() * 101); // Numeri tra 0 e 100
         if (originalArray[i] % 2 == 0) {
            evenArray[evenCount++] = originalArray[i];
         } else {
            oddArray[oddCount++] = originalArray[i];
         }
      }

      // Stampa l'array originale
      System.out.println("Array originale:");
      for (int num : originalArray) {
         System.out.print(num + " ");
      }
      System.out.println();

      // Stampa l'array dei numeri pari
      System.out.println("Numeri pari:");
      for (int i = 0; i < evenCount; i++) {
         System.out.print(evenArray[i] + " ");
      }
      System.out.println();

      // Stampa l'array dei numeri dispari
      System.out.println("Numeri dispari:");
      for (int i = 0; i < oddCount; i++) {
         System.out.print(oddArray[i] + " ");
      }
   }
}

