package pl.appstudiomr.bookaro.catalog.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.appstudiomr.bookaro.catalog.application.port.CatalogUseCase;
import pl.appstudiomr.bookaro.catalog.domain.Book;
import pl.appstudiomr.bookaro.catalog.domain.CatalogRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CatalogService implements CatalogUseCase {
    private final CatalogRepository repository;

    @Override
    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getAuthor().startsWith(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        return Optional.empty();
    }

    @Override
    public void addBook(CreateBookCommand command) {
        Book book = new Book(command.getTitle(), command.getAuthor(), command.getYear());
        repository.save(book);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void updateBook() {

    }
}
