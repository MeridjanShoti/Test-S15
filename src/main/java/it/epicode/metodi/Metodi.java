package it.epicode.metodi;

import java.util.Scanner;

public class Metodi {
    public void aggiungi (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci il titolo del libro:");
        String title = scanner.nextLine();
        System.out.println("inserisci l'ISBN del libro:");
        int isbn = scanner.nextInt();
        System.out.println("inserisci l'anno di pubblicazione del libro:");
        int annoPubblicazione = scanner.nextInt();
        System.out.println("inserisci l'autore del libro:");
        String autore = scanner.nextLine();
        System.out.println("inserisci il genere del libro:");
        String genere = scanner.nextLine();
    }
}
