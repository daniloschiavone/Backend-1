// Esercizio 4: massimo tra tre numeri
public class metodoes4massimotratrenumeri {
   public static void main(String[] args) {
      System.out.println(massimo(5, 3, 8));
      System.out.println(massimo(10, 2, 7));
   }

   public static int massimo(int a, int b, int c) {
      int max = a;
      if (b > max) {
         max = b;
      }
      if (c > max) {
         max = c;
      }
      return max;
   }
}   