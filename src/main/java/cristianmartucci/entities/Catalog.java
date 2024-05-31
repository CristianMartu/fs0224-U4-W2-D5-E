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

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getYearOfPublication() {
        return yearOfPublication;
    }

    public int getPages() {
        return pages;
    }
//    @Override
//    public String toString() {
//        return ISBN + '#' +  pages + '#' + yearOfPublication + '#' + title;
//    }

//    @Override
//    public String toString() {
//        return "Catalog{" +
//                "ISBN='" + ISBN + '\'' +
//                ", title='" + title + '\'' +
//                ", yearOfPublication=" + yearOfPublication +
//                ", pages=" + pages +
//                '}';
//    }
}
