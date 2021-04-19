package pl.appstudiomr.bookaro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.appstudiomr.bookaro.catalog.application.CatalogController;
import pl.appstudiomr.bookaro.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {
    private final CatalogController catalogController;
    private final String title;
    private final String author;
    private final Long limit;

    ApplicationStartup(CatalogController catalogController,
                       @Value("${bookaro.catalog.query}") String title,
                       @Value("${bookaro.catalog.queryAuthor}") String author,
                       @Value("${bookaro.catalog.limit:3}") Long limit) {
        this.catalogController = catalogController;
        this.title = title;
        this.author = author;
        this.limit = limit;
    }

    @Override
    public void run(String... args) {
        System.out.println("Books by title:");
        List<Book> booksByTitle = catalogController.findByTitle(title);
        booksByTitle.stream().limit(limit).forEach(System.out::println);

        System.out.println("----------");

        System.out.println("Books by author:");
        List<Book> booksByAuthor = catalogController.findByAuthor(author);
        booksByAuthor.stream().limit(limit).forEach(System.out::println);
    }
}
