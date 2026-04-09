// Esercizio 3: numero pari
public class metodoes3numeropari {
   public static void main(String[] args) {
      System.out.println(isPari(5));
      System.out.println(isPari(10));
   }

   public static boolean isPari(int numero) {
      return numero % 2 == 0;
   }
}
