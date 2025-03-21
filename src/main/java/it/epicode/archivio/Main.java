package it.epicode.archivio;

import it.epicode.classi.Periodicita;
import it.epicode.classi.Rivista;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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




                archivio.aggiungiCatalogo(new Rivista());
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
        }
    }
}
