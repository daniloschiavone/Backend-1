TROVA L’ERRORE (debug mentale)
(5 esercizi)
11.
	
if (a = 5) {
    System.out.println("OK");
}
// Soluzione:
if (a = 5) { // ERRORE
    Il problema: Nel costrutto if viene usato 
    l'operatore di assegnazione = invece dell'operatore di confronto ==. 
    In Java, la condizione di un if deve essere strettamente un booleano (true o false). 
    Assegnare 5 ad a non restituisce un booleano, quindi il codice non compila.

La correzione: if (a == 5)

12.	

String nome = null;
System.out.println(nome.length());
// Soluzione:

String nome = null;
System.out.println(nome.length()); // ERRORE

Il problema: Stai cercando di invocare un metodo (.length()) 
su un riferimento che vale null (cioè che non punta a nessun oggetto in memoria).
 Questo farà "crashare" il programma lanciando la famigerata NullPointerException.

La correzione: Assegnare un valore valido alla stringa (es. String nome = "Mario";) 
o aggiungere un controllo (es. if (nome != null)).

13.
	
int[] arr = {1,2,3};
System.out.println(arr[3]);
// Soluzione:

int[] arr = {1,2,3};
System.out.println(arr[3]); // ERRORE

Il problema: Gli array in Java sono "zero-indexed", cioè partono dall'indice 0. 
L'array ha 3 elementi, quindi gli indici validi sono 0, 1 e 2. 
Chiamare l'indice 3 significa cercare il "quarto" elemento, che non esiste.

La correzione: System.out.println(arr[2]); per stampare l'ultimo numero dell'array (il 3).

14.	

System.out.println("Ciao)

// Soluzione:

System.out.println("Ciao) // ERRORE

Il problema: La stringa non è chiusa correttamente e manca il terminatore dell'istruzione. 
Il compilatore si fermerà segnalando l'errore.

La correzione: Servono le doppie virgolette per chiudere la stringa e 
il punto e virgola alla fine: System.out.println("Ciao");

15.
	
double x = 5 / 2;

// Soluzione:

double x = 5 / 2; // COMPILA, MA IL RISULTATO E' 2.0

Il problema: Java valuta l'espressione a destra dell'uguale prima di fare l'assegnazione. 
Poiché sia 5 che 2 sono visti come int, viene eseguita una divisione intera 
(5 diviso 2 fa 2, il resto viene troncato). Solo a quel punto il 2 viene convertito in double e assegnato a x, 
facendola diventare 2.0 anziché l'atteso 2.5.

La correzione: Bisogna dire a Java che almeno uno dei due numeri 
è un decimale: double x = 5.0 / 2; (oppure 5d / 2;).