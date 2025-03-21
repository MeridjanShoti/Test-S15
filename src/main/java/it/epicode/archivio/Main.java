package it.epicode.archivio;

import it.epicode.classi.Catalogo;
import it.epicode.classi.Libro;
import it.epicode.classi.Periodicita;
import it.epicode.classi.Rivista;
import it.epicode.exceptions.WrongSelectionException;
import org.slf4j.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);
        Scanner scMain = new Scanner(System.in);
        Archivio archivio = new Archivio();
        System.out.println("-----------MENU------------");
        System.out.println("1. Aggiungi libro o rivista");
        System.out.println("2. Ricerca per ISBN");
        System.out.println("3. Rimuovi per ISBN");
        System.out.println("4. Ricerca per anno");
        System.out.println("5. Ricerca per autore");
        System.out.println("6. Aggiorna libro o rivista");
        System.out.println("7. Statistiche del catalogo");
        System.out.println("0. Esci");
        System.out.println("----------------------------");
        System.out.println("Scegli un'opzione: ");
        int option = scMain.nextInt();
        scMain.nextLine();
        switch (option) {
            case 1:
                System.out.println("Vuoi aggiungere un libro o una rivista?");
                System.out.println("1. Libro");
                System.out.println("2. Rivista");
                System.out.println("Scegli un'opzione: ");
                int option2 = scMain.nextInt();
                scMain.nextLine();
                switch (option2) {
                    case 1:
                        System.out.println("inserisci il titolo del libro:");
                        String titolo =scMain.nextLine();
                        System.out.println("inserisci l'ISBN del libro:");
                        int isbn = scMain.nextInt();
                        scMain.nextLine();
                        System.out.println("inserisci l'anno di pubblicazione del libro:");
                        int annoPubblicazione = scMain.nextInt();
                        scMain.nextLine();
                        System.out.println("inserisci l'autore del libro:");
                        String autore = scMain.nextLine();
                        System.out.println("inserisci il genere del libro:");
                        String genere = scMain.nextLine();
                        System.out.println("inserisci il numero di pagine del libro:");
                        int numPag = scMain.nextInt();
                        scMain.nextLine();
                        Catalogo catalogo = archivio.aggiungiCatalogo(new Libro(isbn, titolo, annoPubblicazione, numPag, autore, genere));
                        System.out.println("Libro con ISBN " + catalogo.getISBN() + "aggiunto con successo!");
                        break;
                    case 2:
                        System.out.println("inserisci il titolo della rivista:");
                        titolo =scMain.nextLine();
                        System.out.println("inserisci l'ISBN della rivista");
                        isbn = scMain.nextInt();
                        scMain.nextLine();
                        System.out.println("inserisci l'anno di pubblicazione della rivista:");
                        annoPubblicazione = scMain.nextInt();
                        System.out.println("inserisci il numero di pagine della rivista:");
                        numPag = scMain.nextInt();
                        scMain.nextLine();
                        boolean continua = true;
                        Periodicita periodicita = null;
                        do {
                            try {
                                continua = false;
                                System.out.println("seleziona la periodicità");
                                System.out.println("1. Settimanale \n2. Mensile \n3. Semestrale");
                                int intPer = scMain.nextInt();
                                scMain.nextLine();
                                switch (intPer) {
                                    case 1:
                                        periodicita = Periodicita.SETTIMANALE;
                                        break;
                                    case 2:
                                        periodicita = Periodicita.MENSILE;
                                        break;
                                    case 3:
                                        periodicita = Periodicita.SEMESTRALE;
                                        break;
                                    default:
                                        throw new WrongSelectionException("selezione non valida");
                                }
                            } catch (WrongSelectionException e) {
                                logger.error(e.getMessage());
                                continua = true;
                            }
                        } while (continua);
                        archivio.aggiungiCatalogo(new Rivista(isbn, titolo, annoPubblicazione, numPag, periodicita));
                        break;
                }




                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
                case 0:
                System.out.println("Arrivederci!");
            default: logger.error("selezione non valida");

        }
    }
}
