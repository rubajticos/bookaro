package pl.appstudiomr.bookaro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.appstudiomr.bookaro.catalog.domain.CatalogRepository;
import pl.appstudiomr.bookaro.catalog.infrastructure.BestsellerCatalogRepository;
import pl.appstudiomr.bookaro.catalog.infrastructure.SchoolCatalogRepository;

import java.util.Random;

@SpringBootApplication
public class BookaroOnlineStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookaroOnlineStoreApplication.class, args);
    }

    @Bean
    CatalogRepository catalogRepository() {
        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("Wybieram szkolne lektury");
            return new SchoolCatalogRepository();
        } else {
            System.out.println("Wybieram bestsellery");
            return new BestsellerCatalogRepository();
        }

    }
}
