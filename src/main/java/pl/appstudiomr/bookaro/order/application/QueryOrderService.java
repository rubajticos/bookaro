package pl.appstudiomr.bookaro.order.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.appstudiomr.bookaro.order.application.port.QueryOrderUseCase;
import pl.appstudiomr.bookaro.order.domain.Order;
import pl.appstudiomr.bookaro.order.domain.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryOrderService implements QueryOrderUseCase {
    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }
}
