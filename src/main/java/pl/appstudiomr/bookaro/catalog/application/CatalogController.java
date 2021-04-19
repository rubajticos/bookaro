package pl.appstudiomr.bookaro.catalog.application;

import org.springframework.stereotype.Controller;
import pl.appstudiomr.bookaro.catalog.domain.Book;
import pl.appstudiomr.bookaro.catalog.domain.CatalogService;

import java.util.List;

@Controller
public class CatalogController {
    private final CatalogService service;

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    public List<Book> findByTitle(String title) {
        return this.service.findByTitle(title);
    }
}
