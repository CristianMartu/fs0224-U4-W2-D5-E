package cristianmartucci.entities;

import java.time.LocalDate;

public abstract class Catalog {
    protected String ISBN;
    protected String title;
    protected LocalDate yearOfPublication;
    protected int pages;

    public Catalog(String ISBN, int pages, LocalDate yearOfPublication, String title) {
        this.ISBN = ISBN;
        this.pages = pages;
        this.yearOfPublication = yearOfPublication;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", pages=" + pages +
                '}';
    }
}
