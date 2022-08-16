package alex.vis.orderservice.controller;

import alex.vis.orderservice.client.InventoryClient;
import alex.vis.orderservice.dto.OrderDto;
import alex.vis.orderservice.model.Order;
import alex.vis.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {

        boolean isAllProductsInStock = orderDto.getOrderLineItems().stream()
                .allMatch(orderLineItem -> inventoryClient.checkStock(orderLineItem.getScuCode()));

        if (isAllProductsInStock) {
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItems());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);

            return "Order place successfully";
        }

        return "Please try again";
    }
}
