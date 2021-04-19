package pl.appstudiomr.bookaro.catalog.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.appstudiomr.bookaro.catalog.domain.Book;
import pl.appstudiomr.bookaro.catalog.domain.CatalogService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService service;

    public List<Book> findByTitle(String title) {
        return this.service.findByTitle(title);
    }
}
