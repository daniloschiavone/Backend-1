QUIZ RAPIDI (comprensione teorica)
1.	Che differenza c’è tra int e double?

int: È un tipo di dato primitivo utilizzato per memorizzare numeri interi (senza la virgola), occupando 32 bit di memoria.

double: È un tipo primitivo utilizzato per memorizzare numeri decimali (in virgola mobile a doppia precisione), occupando 64 bit di memoria. 

2.	Cosa stampa:
System.out.println(5 + 3 + "ciao"); 

Stampa 8ciao.
L'operatore + in Java viene valutato da sinistra verso destra.
Poiché i primi due operandi (5 e 3) sono numeri,
viene eseguita una normale somma matematica (5 + 3 = 8). 
Successivamente, l'8 incontra una stringa ("ciao"),
quindi l'operatore + diventa di concatenazione, 
unendo il numero alla stringa.

3.	Differenza tra == e .equals()? 

==: È un operatore relazionale che confronta i riferimenti in memoria. Controlla se due variabili puntano esattamente allo stesso oggetto fisico nella memoria Heap (per i tipi primitivi, invece, ne confronta semplicemente il valore).

.equals(): È un metodo della classe Object (spesso sovrascritto, come nella classe String) che confronta il contenuto o lo stato logico di due oggetti. Ad esempio, due stringhe diverse in memoria ma con le stesse identiche lettere restituiranno true con .equals() e false con ==.

4.	A cosa serve l’operatore %? 

È l'operatore modulo. Serve a restituire il resto di una divisione intera tra due numeri.
Esempio: 10 % 3 restituisce 1, perché il 3 sta nel 10 tre volte con il resto di 1. È molto utile, ad esempio, per capire se un numero è pari o dispari (n % 2 == 0).

5.	Cosa fa il break nello switch? 

Il comando break serve a interrompere l'esecuzione del blocco switch non appena un case viene eseguito. Se non si inserisce il break, Java continuerà a eseguire a cascata anche le istruzioni dei case successivi (fenomeno noto come fall-through), a prescindere dal fatto che la condizione sia verificata o meno.

6.	Quando usare for e quando while?

for: Si usa tipicamente quando si conosce a priori il numero di iterazioni da compiere (es. scorrere gli elementi di un array da 0 a N).

while: Si usa quando il numero di iterazioni non è noto in anticipo e il ciclo deve semplicemente continuare finché una determinata condizione rimane vera (es. "continua a leggere dal file finché ci sono righe").

7.	Cos’è una eccezione in Java? 

Un'eccezione (Exception) è un evento anomalo che si verifica durante l'esecuzione di un programma e che ne interrompe il normale flusso di istruzioni (es. cercare di dividere un numero per zero, o tentare di aprire un file inesistente). Le eccezioni in Java sono oggetti e possono essere "catturate" e gestite tramite i blocchi try-catch per evitare che il programma vada in crash.

8.	Differenza tra ArrayList e LinkedList? 

ArrayList: Utilizza un array dinamico. È molto veloce per accedere a un elemento specifico tramite il suo indice (get), ma è lento se bisogna inserire o rimuovere elementi a metà della lista (perché deve "spostare" tutti gli altri elementi).

LinkedList: Utilizza una lista doppiamente concatenata (ogni elemento conosce il precedente e il successivo). È molto veloce per aggiungere o rimuovere elementi in qualsiasi posizione, ma è lenta per accedere a un elemento specifico, poiché deve scorrere la lista nodo per nodo.

9.	Cos’è una classe?

In Java (e nella Programmazione Orientata agli Oggetti), una classe è un "progetto" (o template / stampino). Definisce la struttura e le caratteristiche che avranno gli oggetti creati a partire da essa. Una classe è composta da attributi (variabili che ne definiscono lo stato) e metodi (funzioni che ne definiscono il comportamento).

10.	Cosa significa ereditarietà? 

L'ereditarietà è un principio cardine della programmazione orientata agli oggetti (OOP). Permette di creare una nuova classe (chiamata sottoclasse o classe figlia) basata su una classe esistente (superclasse o classe padre). La sottoclasse eredita (acquisisce automaticamente) i metodi e gli attributi pubblici o protetti della superclasse. Questo favorisce enormemente il riutilizzo del codice e permette di creare gerarchie logiche. In Java si realizza con la parola chiave extends.