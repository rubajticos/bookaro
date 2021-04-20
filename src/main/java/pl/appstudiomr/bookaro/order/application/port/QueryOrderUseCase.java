package pl.appstudiomr.bookaro.order.application.port;

import pl.appstudiomr.bookaro.order.domain.Order;

import java.util.List;

public interface QueryOrderUseCase {
    List<Order> findAll();
}
