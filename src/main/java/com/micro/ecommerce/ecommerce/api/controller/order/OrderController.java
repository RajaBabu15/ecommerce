package com.micro.ecommerce.ecommerce.api.controller.order;

import com.micro.ecommerce.ecommerce.model.LocalUser;
import com.micro.ecommerce.ecommerce.model.WebOrder;
import com.micro.ecommerce.ecommerce.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to handle requests to create, update and view orders.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /** The Order Service. */
    private OrderService orderService;

    /**
     * Constructor for spring injection.
     * @param orderService
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint to get all orders for a specific user.
     * @param user The user provided by spring security context.
     * @return The list of orders the user had made.
     */
    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal LocalUser user) {
        return orderService.getOrders(user);
    }

}