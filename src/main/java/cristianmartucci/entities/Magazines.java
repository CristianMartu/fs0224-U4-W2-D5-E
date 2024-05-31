package cristianmartucci.entities;

import cristianmartucci.enums.Periodicity;

import java.time.LocalDate;

public class Magazines extends Catalog {
    public Periodicity periodicity;

    public Magazines(String ISBN, int pages, LocalDate yearOfPublication, String title, Periodicity periodicity) {
        super(ISBN, pages, yearOfPublication, title);
        this.periodicity = periodicity;
    }


    public Periodicity getPeriodicity() {
        return periodicity;
    }

    @Override
    public String toString() {
        return "Magazines{" +
                "periodicity=" + periodicity +
                ", ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", pages=" + pages +
                '}';
    }
}
