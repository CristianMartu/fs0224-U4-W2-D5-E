package cristianmartucci;

import com.github.javafaker.Faker;
import cristianmartucci.entities.*;
import cristianmartucci.enums.Periodicity;

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
        //       List<Catalog> data = createData(10);  //per partire con catalogo pre-riempito
        List<Catalog> data = new ArrayList<>(); // catalogo vuoto
        Archive archive = new Archive(data);
        //      archive.getArchive().forEach(System.out::println);

        System.out.println("_____Aggiunta elementi_____");
        archive.add(new Books("879-0-04-528988-9", 100, LocalDate.parse("2004-06-10"), "The Witcher", "Andrzej Sapkowski", "Fantasy"));
        System.out.println();
        archive.add(new Books("679-0-04-528988-9", 100, LocalDate.parse("2004-06-10"), "The Witcher", "Andrzej Sapkowski", "Fantasy"));
        System.out.println();
        archive.add(new Books("979-0-04-528988-9", 100, LocalDate.parse("1987-06-10"), "The Witcher", "Andrzej Sapkowski", "Fantasy"));
        System.out.println();
        archive.add(new Books("779-0-04-528988-9", 100, LocalDate.parse("1997-06-10"), "The Witcher", "Andrzej Sapkowski", "Fantasy"));

        System.out.println("\n_____Rimozione elementi_____");
        archive.remove("979-0-04-528988-9");
        System.out.println();
        archive.remove("979-0-04-528982-9"); //prova errore

        System.out.println("\n_____Ricerca per ISBN_____");
        try {
            System.out.println(archive.searchByISBN("779-0-04-528988-9"));
            System.out.println(archive.searchByISBN("779-0-04-528982-9")); //prova errore
        } catch (CatalogException error) {
            System.out.println(error.getMessage());
        }

        System.out.println("\n_____Ricerca per anno di pubblicazione_____");
        try {
            System.out.println(archive.serchByYear(LocalDate.parse("2004-06-10")));
            System.out.println(archive.serchByYear(LocalDate.parse("2004-07-10"))); //prova errore
        } catch (CatalogException error) {
            System.out.println(error.getMessage());
        }

    }

}
