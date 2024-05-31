package cristianmartucci.entities;

import java.time.LocalDate;

public class Books extends Catalog {
    private final String author;
    private final String type;

    public Books(String ISBN, int pages, LocalDate yearOfPublication, String title, String author, String type) {
        super(ISBN, pages, yearOfPublication, title);
        this.author = author;
        this.type = type;
    }

    public String getAuthor() {
        return author;
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
