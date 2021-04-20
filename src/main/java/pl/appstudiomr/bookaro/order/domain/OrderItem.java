package pl.appstudiomr.bookaro.order.domain;

import lombok.Value;
import pl.appstudiomr.bookaro.catalog.domain.Book;

@Value
public class OrderItem {
    Book book;
    int quantity;
}
