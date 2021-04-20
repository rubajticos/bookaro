package pl.appstudiomr.bookaro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.appstudiomr.bookaro.catalog.application.port.CatalogUseCase;
import pl.appstudiomr.bookaro.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {
    private final CatalogUseCase catalog;
    private final String title;
    private final String author;
    private final Long limit;

    ApplicationStartup(CatalogUseCase catalog,
                       @Value("${bookaro.catalog.query}") String title,
                       @Value("${bookaro.catalog.queryAuthor}") String author,
                       @Value("${bookaro.catalog.limit:3}") Long limit) {
        this.catalog = catalog;
        this.title = title;
        this.author = author;
        this.limit = limit;
    }

    @Override
    public void run(String... args) {
        initData();
        findByTitle();
        System.out.println("----------");
        findByAuthor();
    }

    private void initData() {
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Pan Tadeusz", "Adam Mickiewicz", 1834));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Ogniem i Mieczem", "Henryk Sienkiewicz", 1884));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Chłopi", "Władysław Reymont", 1904));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Pan Wołodyjowski", "Henryk Sienkiewicz", 1899));
    }

    private void findByAuthor() {
        System.out.println("Books by author:");
        List<Book> booksByAuthor = catalog.findByAuthor(author);
        booksByAuthor.stream().limit(limit).forEach(System.out::println);
    }

    private void findByTitle() {
        System.out.println("Books by title:");
        List<Book> booksByTitle = catalog.findByTitle(title);
        booksByTitle.stream().limit(limit).forEach(System.out::println);
    }
}
