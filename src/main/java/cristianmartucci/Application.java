package cristianmartucci;

import com.github.javafaker.Faker;
import cristianmartucci.entities.Archive;
import cristianmartucci.entities.Books;
import cristianmartucci.entities.Catalog;
import cristianmartucci.entities.Magazines;
import cristianmartucci.enums.Periodicity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Application {
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

    public static void main(String[] args) {
        List<Catalog> data = createData(10);
        Archive archive = new Archive(data);
        archive.getArchive().forEach(System.out::println);
    }

    public static List<Catalog> createData(int quantity) {
        List<Catalog> archive = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            archive.add(createRandomDataCatalog(new Random().nextBoolean()));
        }
        return archive;
    }
}
