package pl.appstudiomr.bookaro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.appstudiomr.bookaro.catalog.application.CatalogController;
import pl.appstudiomr.bookaro.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {
    private final CatalogController catalogController;

    public ApplicationStartup(CatalogController catalogController) {
        this.catalogController = catalogController;
    }

    @Override
    public void run(String... args) {
        List<Book> books = catalogController.findByTitle("Pan");
        books.forEach(System.out::println);
    }
}
