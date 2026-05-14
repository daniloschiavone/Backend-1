package Lezione9;

import java.util.Scanner;

//  È il punto di avvio del programma e l'interfaccia utente a riga di comando.
//  Non deve contenere logica o codice di accesso ai dati. 
//  Gestisce la stampa del menu testuale confinato all'interno di un ciclo che si interrompe unicamente quando l'utente immette il valore "0" . 
//  Si occupa di acquisire gli input da console in base all'opzione selezionata
//  (da 1 a 10) per poi passare i parametri ai metodi di BibliotecaService necessari per eseguire l'azione . 

//Menu principale
//La classe Main deve mostrare un menu testuale.
//Il menu deve rimanere attivo finché l’utente non sceglie 0.
//Menu richiesto:
//===== GESTIONE BIBLIOTECA SCOLASTICA =====
//1. Registra studente
//2. Registra docente
//3. Registra libro
//4. Visualizza tutti gli utenti
//5. Visualizza tutti i libri
//6. Visualizza libri disponibili
//7. Crea prestito
//8. Restituisci libro
//9. Visualizza prestiti attivi
//10. Visualizza storico prestiti di un utente
//0. Esci
//Scelta:
//Funzionamento delle opzioni del menu
//Opzione 1 — Registra studente
//Il programma deve chiedere:
//Nome:
//Cognome:
//Email:
//Classe:
//Poi deve salvare lo studente nel database.
//Opzione 2 — Registra docente
//Il programma deve chiedere:
//Nome:
//Cognome:
//Email:
//Materia:
//Poi deve salvare il docente nel database.
//Opzione 3 — Registra libro
//Il programma deve chiedere:
//Titolo:
//Autore:
//Anno di pubblicazione:
//Poi deve salvare il libro nel database.
//Opzione 4 — Visualizza tutti gli utenti
//Il programma deve stampare tutti gli utenti registrati.
//Esempio:
//ID: 1 | Mario Rossi | mario.rossi@email.com | STUDENTE | Classe: 4A
//ID: 2 | Laura Verdi | laura.verdi@email.com | DOCENTE | Materia: Informatica
//Opzione 5 — Visualizza tutti i libri
//Il programma deve stampare tutti i libri presenti nel database.
//Esempio:
//ID: 1 | Java Base | Marco Riva | 2020 | Disponibile: true
//ID: 2 | Database MySQL | Anna Galli | 2021 | Disponibile: false
//Opzione 6 — Visualizza libri disponibili
//Il programma deve stampare solo i libri disponibili.
//Opzione 7 — Crea prestito
//Il programma deve chiedere:
//ID utente:
//ID libro:
//Poi deve creare il prestito solo se tutte le regole sono rispettate.
//Opzione 8 — Restituisci libro
//Il programma deve chiedere:
//ID prestito:
//Poi deve restituire il libro aggiornando il database.
//Opzione 9 — Visualizza prestiti attivi
//Il programma deve stampare tutti i prestiti con stato ATTIVO.
//Opzione 10 — Visualizza storico prestiti di un utente
//Il programma deve chiedere:
//ID utente:
//Poi deve stampare tutti i prestiti di quell’utente, sia attivi sia restituiti.
//10. Dati obbligatori per il test
//Gli studenti devono inserire almeno i seguenti dati.
//Studenti
//Mario Rossi | mario.rossi@email.com | 4A
//Giulia Bianchi | giulia.bianchi@email.com | 5B
//Ahmed Benali | ahmed.benali@email.com | 3C
//Docenti
//Laura Verdi | laura.verdi@email.com | Informatica
//Paolo Neri | paolo.neri@email.com | Matematica
//Libri
//Java Base | Marco Riva | 2020
//Programmazione OOP | Anna Galli | 2021
//Database MySQL | Fabio Costa | 2022
//Algoritmi e Strutture Dati | Sara Conti | 2019
//Reti Informatiche | Luca Ferri | 2018
//HTML e CSS | Marta Leone | 2023
//Sistemi Operativi | Andrea Romano | 2020

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaService service = new BibliotecaService();
        boolean continua = true;

        while (continua) {
            System.out.println("===== GESTIONE BIBLIOTECA SCOLASTICA =====");
            System.out.println("1. Registra studente");
            System.out.println("2. Registra docente");
            System.out.println("3. Registra libro");
            System.out.println("4. Visualizza tutti gli utenti");
            System.out.println("5. Visualizza tutti i libri");
            System.out.println("6. Visualizza libri disponibili");
            System.out.println("7. Crea prestito");
            System.out.println("8. Restituisci libro");
            System.out.println("9. Visualizza prestiti attivi");
            System.out.println("10. Visualizza storico prestiti di un utente");
            System.out.println("0. Esci");
            int scelta = leggiIntero(scanner, "Seleziona un'opzione: ");

            switch (scelta) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeStudente = scanner.nextLine();
                    System.out.print("Cognome: ");
                    String cognomeStudente = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailStudente = scanner.nextLine();
                    System.out.print("Classe: ");
                    String classe = scanner.nextLine();
                    service.registraStudente(nomeStudente, cognomeStudente, emailStudente, classe);
                    break;
                case 2:
                    System.out.print("Nome: ");
                    String nomeDocente = scanner.nextLine();
                    System.out.print("Cognome: ");
                    String cognomeDocente = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailDocente = scanner.nextLine();
                    System.out.print("Materia: ");
                    String materia = scanner.nextLine();
                    service.registraDocente(nomeDocente, cognomeDocente, emailDocente, materia);
                    break;
                case 3:
                    System.out.print("Titolo: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Autore: ");
                    String autore = scanner.nextLine();
                    int anno = leggiIntero(scanner, "Anno pubblicazione: ");
                    service.registraLibro(titolo, autore, anno);
                    break;
                case 4:
                    service.stampaTuttiGliUtenti();
                    break;
                case 5:
                    service.stampaTuttiILibri();
                    break;
                case 6:
                    service.stampaLibriDisponibili();
                    break;
                case 7:
                    int idUtente = leggiIntero(scanner, "ID utente: ");
                    int idLibro = leggiIntero(scanner, "ID libro: ");
                    service.creaPrestito(idUtente, idLibro);
                    break;
                case 8:
                    int idPrestito = leggiIntero(scanner, "ID prestito: ");
                    service.restituisciLibro(idPrestito);
                    break;
                case 9:
                    service.stampaPrestitiAttivi();
                    break;
                case 10:
                    int idUtenteStorico = leggiIntero(scanner, "ID utente: ");
                    service.stampaStoricoPrestitiUtente(idUtenteStorico);
                    break;
                case 0:
                    continua = false;
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        }

        scanner.close();
    }

    private static int leggiIntero(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            scanner.nextLine();
            System.out.println("Input non valido.");
        }
    }
}
