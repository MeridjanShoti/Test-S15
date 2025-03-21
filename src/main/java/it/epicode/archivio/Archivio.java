package it.epicode.archivio;

import it.epicode.classi.Catalogo;
import it.epicode.classi.Libro;
import it.epicode.classi.Periodicita;
import it.epicode.classi.Rivista;
import it.epicode.exceptions.ISBNException;
import it.epicode.exceptions.WrongSelectionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archivio {
    Logger logger = org.slf4j.LoggerFactory.getLogger(Archivio.class);
    List<Catalogo> archivio = new ArrayList<>();

    public Catalogo aggiungiCatalogo(Catalogo catalogo) {
        if (archivio.stream().anyMatch(c -> c.getISBN() == catalogo.getISBN())) {
            throw new IllegalArgumentException("Catalogo con ISBN " + catalogo.getISBN() + " già presente nell'archivio.");
        } else {
            archivio.add(catalogo);
        }
        return catalogo;
    }

    public Catalogo ricercaCatalogo(int ISBN) throws ISBNException {
        Catalogo catalogo = archivio.stream().filter(c -> c.getISBN() == ISBN).findFirst().orElseThrow(() -> new ISBNException("Catalogo con ISBN " + ISBN + " non trovato nell'archivio."));
        return catalogo;

    }

    public Catalogo rimuoviCatalogo(int ISBN) throws ISBNException {

        Catalogo catalogo = archivio.stream().filter(c -> c.getISBN() == ISBN).findFirst().orElseThrow(() -> new ISBNException("Catalogo con ISBN " + ISBN + " non trovato nell'archivio."));
        archivio.remove(catalogo);
        return catalogo;
    }

    public List<Catalogo> ricercaPerAnnoPubblicazione(int annoPubblicazione) {
        return archivio.stream().filter(c -> c.getAnnoPubblicazione() == annoPubblicazione).toList();
    }

    public List<Catalogo> ricercaPerAutore(String autore) {

        return archivio.stream().filter(c -> c instanceof Libro && ((Libro) c).getAuthor().equals(autore)).toList();

    }

    public void aggiornaCatalogo(Catalogo catalogo) {
        try {
            Scanner sc = new Scanner(System.in);
            if (catalogo instanceof Libro) {
                System.out.println("inserisci nuovo autore");
                ((Libro) catalogo).setAuthor(sc.nextLine());
                System.out.println("inserisci nuovo genere");
                ((Libro) catalogo).setGenre(sc.nextLine());
            } else {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//vedo se è un'istanza di libro o rivista
        //verifico se nella lista esiste un libro o una rivista con quell'ISBN
        // se non esiste lancio un'eccezione
        // se esiste aggiorno


    }
}




