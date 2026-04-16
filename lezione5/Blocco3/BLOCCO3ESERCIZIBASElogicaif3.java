
//Verificare login con username e password
import java.util.Scanner;

public class BLOCCO3ESERCIZIBASElogicaif3 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Inserisci username: ");
            String username = scanner.nextLine();
            System.out.print("Inserisci password: ");
            String password = scanner.nextLine();
    
            // Supponiamo che le credenziali corrette siano "admin" e "password123"
            if (username.equals("admin") && password.equals("password123")) {
                System.out.println("Login riuscito!");
            } else {
                System.out.println("Username o password errati.");
            }
    
            scanner.close();
        }    
    
}
