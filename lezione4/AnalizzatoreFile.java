package lezione4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AnalizzatoreFile {

    public static void main(String[] args) {

        Path file = Paths.get("dati.txt");

        // Controllo esistenza file
        if (Files.exists(file)) {
            System.out.println("Il file esiste.");
        } else {
            System.out.println("Il file non esiste.");
            System.out.println("Creazione file.");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
                writer.write("30\n");
                writer.write("Questa e' la seconda riga (2).\n");
                writer.write("\n");
                writer.write("1593%#@$)(@$!@()53\n");
                writer.write("10\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("");

        // Lettura file
        try {
            List<String> lines = Files.readAllLines(file);
            if (lines.isEmpty()) {
                System.out.println("File vuoto. Nessun dato da analizzare.");
                return;
            }

            // Analisi righe
            int somma = 0, validi = 0, nonValidi = 0;
            for (String line : lines) {
                String cleanLine = line.trim();
                if (cleanLine.isEmpty()) {
                    nonValidi++;
                } else {
                    try {
                        int numero = Integer.parseInt(cleanLine);
                        somma += numero;
                        validi++;

                    } catch (NumberFormatException e) {
                        nonValidi++;
                        System.out.println("Valore non valido trovato: " + cleanLine);
                    }
                }
            }
                        if (validi > 0) {
                // Calcolo media
                double media = (double) somma / validi;
                System.out.println("\n--- REPORT ---");
                System.out.println("Valori validi: " + validi);
                System.out.println("Valori non validi: " + nonValidi);
                System.out.println("Media: " + media);
            } else {
                System.out.println("\nMedia non calcolabile: nessun valore valido.");
            }

        } catch (IOException e) {
            System.out.println("Errore lettura file.");
        }
    }
}