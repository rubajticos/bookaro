package pl.appstudiomr.bookaro.catalog.application.port;

import lombok.Value;
import pl.appstudiomr.bookaro.catalog.domain.Book;

import java.util.List;
import java.util.Optional;

public interface CatalogUseCase {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findAll();

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    void addBook(CreateBookCommand comand);

    void removeById(Long id);

    void updateBook();

    @Value
    class CreateBookCommand {
        String title;
        String author;
        Integer year;
    }
}
