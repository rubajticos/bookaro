package pl.appstudiomr.bookaro.catalog.application.port;

import pl.appstudiomr.bookaro.catalog.domain.Book;

import java.util.List;
import java.util.Optional;

public interface CatalogUseCase {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findAll();

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    void addBook();

    void removeById(Long id);

    void updateBook();
}
