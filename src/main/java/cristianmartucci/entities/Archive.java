package cristianmartucci.entities;

import java.util.List;

public class Archive {
    private List<Catalog> archive;

    public Archive(List<Catalog> archive) {
        this.archive = archive;
    }

    public List<Catalog> getArchive() {
        return archive;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "archive=" + archive +
                '}';
    }
}
