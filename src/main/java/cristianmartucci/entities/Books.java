package cristianmartucci.entities;

import java.time.LocalDate;

public class Books extends Catalog {
    private String author;
    private String type;

    public Books(String ISBN, int pages, LocalDate yearOfPublication, String title, String author, String type) {
        super(ISBN, pages, yearOfPublication, title);
        this.author = author;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Books{" +
                "author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", pages=" + pages +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
