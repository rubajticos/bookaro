package pl.appstudiomr.bookaro.catalog.infrastructure;

import org.springframework.stereotype.Repository;
import pl.appstudiomr.bookaro.catalog.domain.Book;
import pl.appstudiomr.bookaro.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
class MemoryCatalogRepository implements CatalogRepository {
    private final Map<Long, Book> storage = new ConcurrentHashMap<>();
    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);

    public MemoryCatalogRepository() {
//        storage.put(1L, new Book(1L, "Harry Potter i Komnata Tajemnic", "JK Rowlinc", 1998));
//        storage.put(2L, new Book(2L, "Władca Pierścieni: Dwie Wieże", "JRR Tolkien", 1954));
//        storage.put(3L, new Book(3L, "Mężczyźni, którzy nienawidzą kobiet", "Stieg Larsson", 2005));
//        storage.put(4L, new Book(4L, "Sezon Burz", "Andrzej Sapkowski", 2013));

    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void save(Book book) {
        long nextId = nextId();
        book.setId(nextId);
        storage.put(nextId, book);
    }

    private long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }
}
