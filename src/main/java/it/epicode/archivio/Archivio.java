package it.epicode.archivio;

import it.epicode.classi.Catalogo;
import it.epicode.classi.Libro;
import it.epicode.classi.Periodicita;
import it.epicode.classi.Rivista;
import it.epicode.exceptions.ISBNException;
import it.epicode.exceptions.WrongSelectionException;
import lombok.Data;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class Archivio {
    Logger logger = org.slf4j.LoggerFactory.getLogger(Archivio.class);
    List<Catalogo> archivio = new ArrayList<>();
    public void aggiungiCatalogo(Catalogo catalogo) {
        if (archivio.stream().anyMatch(c -> c.getISBN() == catalogo.getISBN())) {
            throw new IllegalArgumentException("Catalogo con ISBN " + catalogo.getISBN() + " già presente nell'archivio.");
        } else {
            archivio.add(catalogo);
        }
        }
    public void ricercaCatalogo(int ISBN) throws ISBNException {
        Catalogo catalogo = archivio.stream().filter(c -> c.getISBN() == ISBN).findFirst().orElse(null);
        if (catalogo != null) {
            System.out.println("Catalogo trovato:");
            System.out.println(catalogo);
        } else {
            throw new ISBNException("Catalogo con ISBN " + ISBN + " non trovato nell'archivio.");
        }
    }
    public void rimuoviCatalogo(int ISBN) throws ISBNException {
        Catalogo catalogo = archivio.stream().filter(c -> c.getISBN() == ISBN).findFirst().orElse(null);
        if (catalogo != null) {
            archivio.remove(catalogo);
            System.out.println("Catalogo con ISBN " + ISBN + " rimosso dall'archivio.");
        } else {
            throw new ISBNException("Catalogo con ISBN " + ISBN + " non trovato nell'archivio.");
        }
    }
    public void ricercaPerAnnoPubblicazione(int annoPubblicazione) {
        List<Catalogo> cataloghi = archivio.stream().filter(c -> c.getAnnoPubblicazione() == annoPubblicazione).toList();
        if (cataloghi.isEmpty()) {
            System.out.println("Nessun catalogo trovato per l'anno di pubblicazione " + annoPubblicazione);
        } else {
            System.out.println("Cataloghi trovati per l'anno di pubblicazione " + annoPubblicazione + ":");
            cataloghi.forEach(System.out::println);
        }
    }
    public void RicercaPerAutore(String autore) {

        List<Catalogo> cataloghi = archivio.stream().filter(c -> c instanceof Libro && ((Libro) c).getAuthor().equals(autore)).toList();
        if (cataloghi.isEmpty()) {
            System.out.println("Nessun catalogo trovato per l'autore " + autore);
        } else {
            System.out.println("Cataloghi trovati per l'autore " + autore + ":");
            cataloghi.forEach(System.out::println);
        }
    }
    public void aggiornaCatalogo(Catalogo catalogo) {
        Scanner sc = new Scanner(System.in);
        if(catalogo instanceof Libro){
            System.out.println("inserisci nuovo autore");
            ((Libro) catalogo).setAuthor(sc.nextLine());
            System.out.println("inserisci nuovo genere");
            ((Libro) catalogo).setGenre(sc.nextLine());
        }
        else {
            boolean continua = false;
            do {
                try {
                    continua = false;
                    System.out.println("seleziona la nuova periodicità");
                    System.out.println("1. Settimanale \n2. Mensile \n3. Semestrale");
                    int intPer = sc.nextInt();
                    switch (intPer) {
                        case 1:
                            ((Rivista) catalogo).setPeriodicita(Periodicita.SETTIMANALE);
                            break;
                        case 2:
                            ((Rivista) catalogo).setPeriodicita(Periodicita.MENSILE);
                            break;
                        case 3:
                            ((Rivista) catalogo).setPeriodicita(Periodicita.SEMESTRALE);
                            break;
                        default:
                            throw new WrongSelectionException("selezione non valida");
                    }
                } catch (WrongSelectionException e) {
                    logger.error(e.getMessage());
                    continua = true;
                }
            } while (continua);
        }
        System.out.println("inserisci nuovo titolo");
        catalogo.setTitle(sc.nextLine());
        System.out.println("inserisci nuovo anno pubblicazione");
        catalogo.setAnnoPubblicazione(sc.nextInt());
        System.out.println("inserisci nuovo numero di pagine");
        catalogo.setNumeroPagine(sc.nextInt());
        sc.nextLine();



    }
    }




