package pl.appstudiomr.bookaro.order.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Order {
    @Builder.Default
    private OrderStatus status = OrderStatus.NEW;
    private List<OrderItem> items;
    private Recipient recipient;
    private LocalDateTime createdAt;
    private Long id;

    BigDecimal totalPrice() {
        return items.stream()
                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
