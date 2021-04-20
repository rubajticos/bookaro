package pl.appstudiomr.bookaro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.appstudiomr.bookaro.catalog.application.port.CatalogUseCase;
import pl.appstudiomr.bookaro.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.appstudiomr.bookaro.catalog.domain.Book;
import pl.appstudiomr.bookaro.order.application.port.PlaceOrderUseCase;
import pl.appstudiomr.bookaro.order.application.port.PlaceOrderUseCase.PlaceOrderCommand;
import pl.appstudiomr.bookaro.order.application.port.QueryOrderUseCase;
import pl.appstudiomr.bookaro.order.domain.OrderItem;
import pl.appstudiomr.bookaro.order.domain.Recipient;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {
    private final CatalogUseCase catalog;
    private final String title;
    private final String author;
    private final Long limit;
    private PlaceOrderUseCase placeOrder;
    private QueryOrderUseCase queryOrder;

    ApplicationStartup(CatalogUseCase catalog,
                       PlaceOrderUseCase placeOrder,
                       QueryOrderUseCase queryOrder,
                       @Value("${bookaro.catalog.query}") String title,
                       @Value("${bookaro.catalog.queryAuthor}") String author,
                       @Value("${bookaro.catalog.limit:3}") Long limit) {
        this.catalog = catalog;
        this.placeOrder = placeOrder;
        this.queryOrder = queryOrder;
        this.title = title;
        this.author = author;
        this.limit = limit;
    }

    @Override
    public void run(String... args) {
        initData();
        searchCatalog();
        placeOrder();
    }

    private void initData() {
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Pan Tadeusz", "Adam Mickiewicz", 1834, new BigDecimal("19.90")));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Ogniem i Mieczem", "Henryk Sienkiewicz", 1884, new BigDecimal("29.90")));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Chłopi", "Władysław Reymont", 1904, new BigDecimal("11.90")));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Pan Wołodyjowski", "Henryk Sienkiewicz", 1899, new BigDecimal("14.90")));
    }

    private void searchCatalog() {
        findByTitle();
        System.out.println("----------");
        findByAuthor();

        findAndUpdate();
        findByTitle();
    }

    private void placeOrder() {
        Book panTadeusz = catalog.findOneByTitle("Pan Tadeusz").orElseThrow(() -> new IllegalStateException("Cannot find a book"));
        Book chlopi = catalog.findOneByTitle("Chłopi").orElseThrow(() -> new IllegalStateException("Cannot find a book"));

        Recipient recipient = Recipient.builder()
                .name("Jan Kowalski")
                .phone("123-546-789")
                .street("Armii Krajowej 31")
                .city("Krakow")
                .zipCode("31-150")
                .email("jan@example.com")
                .build();

        PlaceOrderCommand command = PlaceOrderCommand.builder()
                .recipient(recipient)
                .item(new OrderItem(panTadeusz, 16))
                .item(new OrderItem(chlopi, 7))
                .build();

        PlaceOrderUseCase.PlaceOrderResponse response = placeOrder.placeOrder(command);
        System.out.println("Created ORDER with id: " + response.getOrderId());

        queryOrder.findAll()
                .forEach(order -> System.out.println("GOT ORDER WITH TOTAL PRICE: " + order.totalPrice() + " DETAILS: " + order));
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

    private void findAndUpdate() {
        System.out.println("Updating book.....");
        catalog.findOneByTitleAndAuthor("Pan Tadeusz", "Adam Mickiewicz")
                .ifPresent(book -> {
                    UpdateBookCommand command = UpdateBookCommand.builder()
                            .id(book.getId())
                            .title("Pan Tadeusz czyli Ostatni Zajazd na Litwie")
                            .build();
                    CatalogUseCase.UpdateBookResponse response = catalog.updateBook(command);
                    System.out.println("Updating book result: " + response.isSuccess());
                });

    }
}
