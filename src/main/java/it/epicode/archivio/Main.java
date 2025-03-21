package it.epicode.archivio;

import it.epicode.classi.Catalogo;
import it.epicode.classi.Libro;
import it.epicode.classi.Periodicita;
import it.epicode.classi.Rivista;
import it.epicode.exceptions.WrongSelectionException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);
        Scanner scMain = new Scanner(System.in);
        Archivio archivio = new Archivio();
        boolean menu = true;
        while(menu){
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
            int option = 0;
            try {
                option = scMain.nextInt();
            } catch (InputMismatchException e) {
                logger.error("input errato");
            } finally {
                scMain.nextLine();
            }
            switch (option) {
                case 1:
                    System.out.println("Vuoi aggiungere un libro o una rivista?");
                    System.out.println("1. Libro");
                    System.out.println("2. Rivista");
                    System.out.println("Scegli un'opzione: ");
                    int option2 = 0;
                    try {
                        option2 = scMain.nextInt();
                        if (option2 < 1 || option2 > 2){
                            throw new WrongSelectionException("opzione non valida");
                        }
                    } catch (InputMismatchException e) {
                        logger.error("input non corretto");
                    }
                    catch (WrongSelectionException e) {
                        logger.error(e.getMessage());
                    } finally {
                        scMain.nextLine();
                    }

                    switch (option2) {
                        case 1:
                            System.out.println("inserisci il titolo del libro:");
                            String titolo = scMain.nextLine();
                            System.out.println("inserisci l'ISBN del libro:");
                            int isbn = 0;
                            try {
                                isbn = scMain.nextInt();
                            } catch (InputMismatchException e) {
                                logger.error("input errato");
                            } finally {
                                scMain.nextLine();
                            }
                            System.out.println("inserisci l'anno di pubblicazione del libro:");
                            int annoPubblicazione = 0;
                            try {
                                annoPubblicazione = scMain.nextInt();
                            } catch (InputMismatchException e) {
                                logger.error("input errato");
                            } finally {
                                scMain.nextLine();
                            }
                            System.out.println("inserisci l'autore del libro:");
                            String autore = scMain.nextLine();
                            System.out.println("inserisci il genere del libro:");
                            String genere = scMain.nextLine();
                            System.out.println("inserisci il numero di pagine del libro:");
                            int numPag = 0;
                            try {
                                numPag = scMain.nextInt();
                            } catch (InputMismatchException e) {
                                logger.error("input errato");
                            } finally {
                                scMain.nextLine();
                            }

                            Catalogo catalogo = archivio.aggiungiCatalogo(new Libro(isbn, titolo, annoPubblicazione, numPag, autore, genere));
                            try {
                                System.out.println("Libro con ISBN " + catalogo.getISBN() + "aggiunto con successo!");
                            } catch (Exception e) {
                                logger.error("il libro o la rivista è null");
                            }
                            break;
                        case 2:
                            System.out.println("inserisci il titolo della rivista:");
                            titolo = scMain.nextLine();
                            System.out.println("inserisci l'ISBN della rivista");
                            int isbnRivista = 0;
                            try {
                                isbnRivista = scMain.nextInt();
                            } catch (InputMismatchException e) {
                                logger.error("input errato");
                            } finally {
                                scMain.nextLine();
                            }
                            System.out.println("inserisci l'anno di pubblicazione della rivista:");
                            int annoPubblicazioneRivista = 0;
                            try {
                                annoPubblicazioneRivista = scMain.nextInt();
                            } catch (InputMismatchException e) {
                                logger.error("input errato");
                            } finally {
                                scMain.nextLine();
                            }
                            System.out.println("inserisci il numero di pagine della rivista:");
                            int numPagRivista = 0;
                            try {
                                numPagRivista = scMain.nextInt();
                            } catch (InputMismatchException e) {
                                logger.error("input errato");
                            } finally {
                                scMain.nextLine();
                            }
                            boolean continua = true;
                            Periodicita periodicita = null;
                            do {
                                try {
                                    continua = false;
                                    System.out.println("seleziona la periodicità");
                                    System.out.println("1. Settimanale \n2. Mensile \n3. Semestrale");
                                    int intPer = 0;
                                    try {
                                        intPer = scMain.nextInt();
                                    } catch (InputMismatchException e) {
                                        logger.error("input errato");
                                    } finally {
                                        scMain.nextLine();
                                    }
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
                            archivio.aggiungiCatalogo(new Rivista(isbnRivista, titolo, annoPubblicazioneRivista, numPagRivista, periodicita));
                            break;
                    }


                    break;
                case 2:
                    try {
                        System.out.println("inserisci l'ISBN da cercare:");
                        int isbn = 0;
                        try {
                            isbn = scMain.nextInt();
                        } catch (InputMismatchException e) {
                            logger.error("input non corretto");
                        } finally {
                            scMain.nextLine();
                        }
                        System.out.println(archivio.ricercaCatalogo(isbn));
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("inserisci l'ISBN da rimuovere:");
                        int isbn = 0;
                        try {
                            isbn = scMain.nextInt();
                        } catch (InputMismatchException e) {
                            logger.error("input errato");
                        } finally {
                            scMain.nextLine();
                        }
                        archivio.rimuoviCatalogo(isbn);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("inserisci l'anno da cercare:");
                        int anno = 0;
                        try {
                            anno = scMain.nextInt();
                        } catch (InputMismatchException e) {
                            logger.error("input errato");
                        } finally {
                            scMain.nextLine();
                        }
                        System.out.println(archivio.ricercaPerAnnoPubblicazione(anno));
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("inserisci l'autore da cercare:");
                        String autore = scMain.nextLine();
                        System.out.println(archivio.ricercaPerAutore(autore));
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.println("inserisci l'ISBN del libro da aggiornare:");
                        Catalogo catalogoDaAggiornare = archivio.ricercaCatalogo(scMain.nextInt());
                        archivio.aggiornaCatalogo(catalogoDaAggiornare);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    } finally {
                        scMain.nextLine();
                    }
                    break;
                case 7:
                    archivio.statistiche();
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    menu = false;
                    break;
                default:
                    logger.error("selezione non valida");

            }
            System.out.println("invia un input qualsiasi per continuare");
            scMain.nextLine();
        }
        scMain.close();
    }
}
