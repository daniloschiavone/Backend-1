package lezione6;

/*Traccia:
Si vuole realizzare un programma Java chiamato GestioneStudentiFile che legga da file un
elenco di studenti, riconosca i duplicati, permetta di filtrare gli studenti per classe e salvi il
risultato in un nuovo file di testo.
Specifiche funzionali:
• Ogni riga del file studenti_input.txt è nel formato: matricola;nome;cognome;classe
• Nel file sono presenti duplicati intenzionali che il programma deve identificare ed eliminare.
• L’utente deve inserire da tastiera la classe da estrarre, ad esempio 3A oppure 5B.
• Il programma deve produrre un file di output contenente solo gli studenti univoci della classe
richiesta.
Richieste Obbligatorie:
1. Creare una classe Studente con almeno i campi: matricola, nome, cognome, classe.
2. Leggere il file studenti_input.txt usando FileReader, BufferedReader oppure Scanner su file.
3. Memorizzare inizialmente tutti gli studenti letti in una ArrayList<Studente>.
4. Usare una struttura adeguata per eliminare i duplicati. E richiesta una riflessione sulla scelta
tra ArrayList, HashSet e HashMap.
5. Consentire all'utente di inserire la classe da cercare. Il valore inserito deve essere validato.
6. Estrarre tutti gli studenti appartenenti alla classe richiesta.
7. Salvare il risultato in un file chiamato output_classe_<classe>.txt. Esempio:
output_classe_3A.txt.
8. Ogni riga del file di output deve mantenere lo stesso formato del file di input.
9. Stampare a video: numero totale righe lette, numero duplicati trovati, numero studenti unici,
numero studenti della classe selezionata.
10. Usare almeno un blocco try/catch/finally nella gestione del file o dell'input da tastiera.
11. Gestire almeno un possibile input non valido dell'utente, per esempio una classe vuota o
inesistente.
12. Organizzare il programma in metodi separati e non scrivere tutta la logica nel main*/

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ESesameGestioneStudentiFile {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Danil\\Documents\\GitHub\\Backend 1\\lezione6\\studenti_input.txt";
        String classiFile ="C:\\Users\\Danil\\Documents\\GitHub\\Backend 1\\lezione6\\classi_disponibili.txt";
       
        // 1. Lettura file e memorizzazione iniziale in ArrayList
        ReadResult readResult = leggiStudenti(inputFile);

        // CONTROLLO: Se non sono stati letti studenti, non ha senso procedere con il resto del programma
        if (readResult.studenti.isEmpty()) {
            System.out.println("Nessuno studente caricato. Impossibile procedere.");
            return; // Termina il programma
        }
        
        // 2. Rimozione duplicati e riflessione sulle strutture
        // Nota: Si usa LinkedHashMap per gestire i duplicati tramite matricola (chiave)
        // mantenendo l'ordine originale. HashSet richiederebbe override di equals/hashCode in Studente.
        DedupResult dedupResult = deduplicaPerMatricola(readResult.studenti);

        // 3. Caricamento classi valide per la validazione
        Set<String> classiDisponibili = leggiClassiDisponibili(classiFile);
        if (classiDisponibili.isEmpty()) {
            classiDisponibili = estraiClassiDaStudenti(dedupResult.studentiUnici);
        }

        // 4. Input utente con validazione
        Scanner scanner = new Scanner(System.in);
        String classeCercata = "";
        try {
            classeCercata = chiediClasse(scanner, classiDisponibili);
        } catch (Exception e) {
            System.out.println("Errore imprevisto durante l'input: " + e.getMessage());
            return;
        } finally {
            // Messaggio di chiusura operazione come richiesto dalla traccia
            System.out.println("Operazione di acquisizione input terminata.");
        }

        // 5. Filtraggio studenti
        List<Studente> studentiFiltrati = filtraPerClasse(dedupResult.studentiUnici, classeCercata);

        // 6. Salvataggio su file di output
        String outputFile = "output_classe_" + classeCercata + ".txt";
        scriviStudenti(outputFile, studentiFiltrati);

        // 7. Stampe statistiche finali
        stampaStatistiche(readResult.righeLette, dedupResult.duplicati, 
                          dedupResult.studentiUnici.size(), studentiFiltrati.size(), classeCercata);

        // 8. Simulazioni eccezioni richieste
        eseguiSimulazioniEccezioni();
    }

    private static ReadResult leggiStudenti(String inputFile) {
        List<Studente> lista = new ArrayList<>();
        int contatoreRighe = 0;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = br.readLine()) != null) {
                contatoreRighe++;
                String[] dati = line.split(";"); // Formato richiesto: matricola;nome;cognome;classe
                if (dati.length == 4) {
                    lista.add(new Studente(dati[0].trim(), dati[1].trim(), dati[2].trim(), dati[3].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Errore: Impossibile leggere il file di input (" + e.getMessage() + ")");
        } finally {
            // Messaggio di chiusura richiesto dalla traccia
            System.out.println("Operazione di lettura file completata.");
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                System.out.println("Errore nella chiusura del BufferedReader.");
            }
        }
        return new ReadResult(lista, contatoreRighe);
    }

    private static DedupResult deduplicaPerMatricola(List<Studente> listaCompleta) {
        Map<String, Studente> mappaUnici = new LinkedHashMap<>();
        int duplicatiTrovati = 0;

        for (Studente s : listaCompleta) {
            if (mappaUnici.containsKey(s.getMatricola())) {
                duplicatiTrovati++;
            } else {
                mappaUnici.put(s.getMatricola(), s);
            }
        }
        return new DedupResult(new ArrayList<>(mappaUnici.values()), duplicatiTrovati);
    }

    private static String chiediClasse(Scanner sc, Set<String> valide) {
        while (true) {
            System.out.print("Inserisci la classe da estrarre (es. 3A): ");
            String input = sc.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Errore: Il campo non può essere vuoto.");
                continue;
            }
            if (!valide.isEmpty() && !valide.contains(input)) {
                System.out.println("Errore: Classe inesistente. Riprova.");
                continue;
            }
            return input;
        }
    }

    private static List<Studente> filtraPerClasse(List<Studente> unici, String classe) {
        List<Studente> filtrati = new ArrayList<>();
        for (Studente s : unici) {
            if (s.getClasse().equalsIgnoreCase(classe)) {
                filtrati.add(s);
            }
        }
        return filtrati;
    }

    private static void scriviStudenti(String filename, List<Studente> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Studente s : lista) {
                pw.println(s.toFileLine());
            }
            System.out.println("Risultato salvato con successo in: " + filename);
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file di output.");
        }
    }

    private static void stampaStatistiche(int lette, int dup, int unici, int filtrati, String classe) {
        System.out.println("\n--- RIEPILOGO OPERAZIONI ---");
        System.out.println("Numero totale righe lette: " + lette);
        System.out.println("Numero duplicati identificati: " + dup);
        System.out.println("Numero studenti unici: " + unici);
        System.out.println("Studenti estratti per la classe " + classe + ": " + filtrati);
        System.out.println("---------------------------\n");
    }

    private static void eseguiSimulazioniEccezioni() {
        System.out.println("Avvio simulazioni eccezioni richieste...");
        simulaNullPointerException();
        simulaArrayIndexOutOfBoundsException();
    }

    private static void simulaNullPointerException() {
        try {
            String testo = null;
            System.out.println(testo.length());
        } catch (NullPointerException e) {
            System.out.println("Catturata eccezione simulata: NullPointerException gestita correttamente.");
        }
    }

    private static void simulaArrayIndexOutOfBoundsException() {
        try {
            int[] numeri = {1, 2, 3};
            int errore = numeri[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Catturata eccezione simulata: ArrayIndexOutOfBoundsException gestita correttamente.");
        }
    }

    // Metodi di supporto per caricare le classi se il file classi_disponibili.txt esiste
    private static Set<String> leggiClassiDisponibili(String file) {
        Set<String> classi = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                if (!riga.trim().isEmpty()) classi.add(riga.trim().toUpperCase());
            }
        } catch (IOException e) {
            // Se il file non esiste, procederemo estraendo le classi dagli studenti
        }
        return classi;
    }

    private static Set<String> estraiClassiDaStudenti(List<Studente> lista) {
        Set<String> classi = new HashSet<>();
        for (Studente s : lista) classi.add(s.getClasse().toUpperCase());
        return classi;
    }

    // Classi interne per gestire i risultati multipli dei metodi
    private static class ReadResult {
        List<Studente> studenti;
        int righeLette;
        ReadResult(List<Studente> s, int r) { this.studenti = s; this.righeLette = r; }
    }

    private static class DedupResult {
        List<Studente> studentiUnici;
        int duplicati;
        DedupResult(List<Studente> s, int d) { this.studentiUnici = s; this.duplicati = d; }
    }
}