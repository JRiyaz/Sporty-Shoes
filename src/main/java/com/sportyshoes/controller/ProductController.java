package com.sportyshoes.controller;

import com.sportyshoes.entity.ProductEntity;
import com.sportyshoes.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("add-stock")
    public String addStock(ProductEntity productEntity) {

        return "add-stock";
    }

    @PostMapping("add-stock")
    public String addStock(@Valid ProductEntity productEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "add-stock";

        productService.save(productEntity);
        return "redirect:/?add-stock=true";
    }
}
