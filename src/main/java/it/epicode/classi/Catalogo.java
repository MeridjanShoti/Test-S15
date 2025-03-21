package it.epicode.classi;

import lombok.Data;

@Data
public abstract class Catalogo {
    private int ISBN;
    private String title;
    private int annoPubblicazione;
    private int numeroPagine;
}
