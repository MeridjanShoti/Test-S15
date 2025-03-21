package it.epicode.classi;

public class Rivista extends Catalogo {
    private Periodicita periodicita;

    public Rivista(int ISBN, String title, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(ISBN, title, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "ISBN=" + this.getISBN() +
                ", title='" + this.getTitle() + '\'' +
                ", annoPubblicazione=" + this.getAnnoPubblicazione() +
                ", numeroPagine=" + this.getNumeroPagine() +
                ", periodicita=" + periodicita +
                '}';
    }
}
