package cristianmartucci.entities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Archive {
    private final List<Catalog> archive;

    public Archive(List<Catalog> archive) {
        this.archive = archive;
    }

    public static String read(File file) throws IOException {
        String readFile = FileUtils.readFileToString(file, "UTF-8");
//        System.out.println(readFile);
        return readFile;
    }

//    public static void save(Archive archive) {
//        File file = new File("src/save.txt");
//        try {
//            FileUtils.writeStringToFile(file, archive.toString(), StandardCharsets.UTF_8);
//            System.out.println("File salvato correttamente");
//        } catch (IOException error) {
//            System.out.println(error.getMessage());
//        }
//    }

    public void save() {
        List<String> string = new ArrayList<>();
        for (Catalog catalog : archive) {
            if (catalog instanceof Books book) {
                string.add("Books-" + book.getISBN() + "-" + book.getPages() + "-" +
                        book.getYearOfPublication() + "-" + book.getTitle() + "-" +
                        book.getAuthor() + "-" + book.getType());
            } else if (catalog instanceof Magazines magazine) {
                string.add("Magazines-" + magazine.getISBN() + "-" + magazine.getPages() + "-" +
                        magazine.getYearOfPublication() + "-" + magazine.getTitle() + "-" +
                        magazine.getPeriodicity());
            }
        }
        File file = new File("src/save.txt");
        try {
            FileUtils.writeStringToFile(file, String.valueOf(string), StandardCharsets.UTF_8);
            System.out.println("File salvato correttamente");
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public List<Catalog> getArchive() {
        return archive;
    }

    public void add(Catalog element) {
        archive.add(element);
        archive.forEach(System.out::println);
    }

    public void remove(String ISBN) {
        List<Catalog> elementToRemove = archive.stream().filter(catalog -> Objects.equals(catalog.ISBN, ISBN)).toList();
        if (!elementToRemove.isEmpty()) {
            archive.remove(elementToRemove.getFirst());
            archive.forEach(System.out::println);
        } else System.out.println("Nessum elemento trovato con ISBN: " + ISBN);
    }

    public Catalog searchByISBN(String ISBN) throws CatalogException {
        List<Catalog> result = archive.stream().filter(catalog -> Objects.equals(catalog.ISBN, ISBN)).toList();
        if (!result.isEmpty()) {
            return result.getFirst();
        } else throw new CatalogException("Nessum elemento trovato con ISBN: " + ISBN);
    }

    public List<Catalog> serchByYear(LocalDate year) throws CatalogException {
        List<Catalog> result = archive.stream().filter(catalog -> Objects.equals(catalog.yearOfPublication, year)).toList();
        if (!result.isEmpty()) {
            return result;
        } else throw new CatalogException("Nessum elemento trovato con data: " + year);
    }

    public List<Catalog> searchByAuthor(String author) throws CatalogException {
        List<Catalog> result = archive.stream().filter(catalog -> catalog instanceof Books).filter(book -> Objects.equals(((Books) book).getAuthor(), author)).toList();
        if (!result.isEmpty()) {
            return result;
        } else throw new CatalogException("Nessum elemento trovato con data: " + author);
    }

    @Override
    public String toString() {
        return "Archive{" +
                "archive=" + archive +
                '}';
    }
}
