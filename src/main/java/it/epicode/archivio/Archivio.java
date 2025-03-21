package it.epicode.archivio;

import it.epicode.classi.Catalogo;
import it.epicode.exceptions.ISBNException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Archivio {
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
    }



