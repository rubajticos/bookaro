package pl.appstudiomr.bookaro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookaroOnlineStoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookaroOnlineStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // tutaj
        CatalogService catalogService = new CatalogService();
        List<Book> books = catalogService.findByTitle("Pan Tadeusz");
        books.forEach(System.out::println);
    }
}
