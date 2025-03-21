package it.epicode.classi;

import lombok.Data;

@Data
public class Libro extends Catalogo{
    private String author;
    private String genre;
}
