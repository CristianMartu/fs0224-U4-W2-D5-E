package cristianmartucci.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Archive {
    private List<Catalog> archive;

    public Archive(List<Catalog> archive) {
        this.archive = archive;
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

    @Override
    public String toString() {
        return "Archive{" +
                "archive=" + archive +
                '}';
    }
}
