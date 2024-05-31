package cristianmartucci;

import com.github.javafaker.Faker;
import cristianmartucci.entities.*;
import cristianmartucci.enums.Periodicity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {
    public static Catalog createRandomDataCatalog(boolean typeCatalog) {
        Faker faker = new Faker();
        Date data = faker.date().birthday();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = format.format(data);
        Periodicity[] periodicities = {Periodicity.MENSILE, Periodicity.SEMESTRALE, Periodicity.SETTIMANALE};

        String isbn = faker.code().isbn13(true);
        String title = faker.book().title();
        LocalDate year = LocalDate.parse(dateFormat);
        int pages = new Random().nextInt(250, 2000);
        String author = faker.book().author();
        String type = faker.book().genre();
        Periodicity periodicity = periodicities[new Random().nextInt(3)];

        int pagesMagazine = new Random().nextInt(20, 150);

        if (typeCatalog) {
            return new Books(isbn, pages, year, title, author, type);
        } else return new Magazines(isbn, pagesMagazine, year, title, periodicity);
    }

    public static List<Catalog> createData(int quantity) {
        List<Catalog> archive = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            archive.add(createRandomDataCatalog(new Random().nextBoolean()));
        }
        return archive;
    }

    public static void main(String[] args) {
        List<Catalog> data = new ArrayList<>();
        Archive archive = new Archive(data);

        System.out.println("_____Aggiunta elementi_____");
        archive.add(new Books("879-0-04-528988-9", 100, LocalDate.parse("2004-06-10"), "The Witcher", "Andrzej Sapkowski", "Fantasy"));
        System.out.println();
        archive.add(new Books("679-0-04-528988-9", 100, LocalDate.parse("2004-06-10"), "The Witcher", "Andrzej Sapkowski", "Fantasy"));
        System.out.println();
        archive.add(new Books("979-0-04-528988-9", 100, LocalDate.parse("1987-06-10"), "The Witcher", "Andrzej Sapkowski", "Fantasy"));
        System.out.println();
        archive.add(new Books("779-0-04-528988-9", 100, LocalDate.parse("1997-06-10"), "Game Of Thrones", "Martin", "Fantasy"));

        System.out.println("\n_____Rimozione elementi_____");
        archive.remove("979-0-04-528988-9");
        System.out.println();
        archive.remove("979-0-04-528982-9"); //prova errore

        System.out.println("\n_____Ricerca per ISBN_____");
        try {
            System.out.println(archive.searchByISBN("779-0-04-528988-9"));
//            System.out.println(archive.searchByISBN("779-0-04-528982-9")); //prova errore
        } catch (CatalogException error) {
            System.out.println(error.getMessage());
        }

        System.out.println("\n_____Ricerca per anno_____");
        try {
            System.out.println(archive.serchByYear(LocalDate.parse("2004-06-10")));
//            System.out.println(archive.serchByYear(LocalDate.parse("2004-07-10"))); //prova errore
        } catch (CatalogException error) {
            System.out.println(error.getMessage());
        }

        System.out.println("\n_____Ricerca per autore_____");
        try {
            System.out.println(archive.searchByAuthor("Andrzej Sapkowski"));
//            System.out.println(archive.searchByAuthor("Prova")); //prova errore
        } catch (CatalogException error) {
            System.out.println(error.getMessage());
        }

        System.out.println("\n_____Elementi casuali_____");
        List<Catalog> RandomData = createData(10);  // per partire con catalogo pre-riempito
        Archive randomArchive = new Archive(RandomData);    // perso tempo inutilmente la mattina per la creazione casuale ma visto che l'ho fatto lo lascio :(
        randomArchive.getArchive().forEach(System.out::println);

        System.out.println("\n_____Salvataggio su disco di archivio_____");
        randomArchive.save();

        System.out.println("\n_____Caricamento da disco dell'archivio_____");
        File file = new File("src/save.txt");
        Archive.load(file);

    }

}
