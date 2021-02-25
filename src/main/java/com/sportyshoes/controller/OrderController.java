package com.sportyshoes.controller;

import com.sportyshoes.entity.OrderEntity;
import com.sportyshoes.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("all")
    public String findAll(Model model) {

        final List<OrderEntity> orders = orderService.findAll();

        model.addAttribute("orders", orders);
        return "view-orders";
    }
}
