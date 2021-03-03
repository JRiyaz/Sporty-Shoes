package com.sportyshoes.controller;

import com.sportyshoes.entity.OrderEntity;
import com.sportyshoes.entity.ProductEntity;
import com.sportyshoes.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("all")
    @PreAuthorize("hasAuthority('UPDATE')")
    public String findAll(Model model) {

        final List<OrderEntity> orders = orderService.findAll();

        model.addAttribute("orders", orders);
        return "view-orders";
    }

    @GetMapping("view/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public String viewOrder(@PathVariable Integer id, Model model) {

        final OrderEntity orderEntity = orderService.findById(id).get();
        final List<ProductEntity> products = orderEntity.getProducts();
        model.addAttribute("order", orderEntity);
        model.addAttribute("products", products);
        return "order-details";
    }
}
