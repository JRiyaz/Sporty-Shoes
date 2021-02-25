package com.sportyshoes.controller;

import com.sportyshoes.entity.ProductEntity;
import com.sportyshoes.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("all")
    public String findAll(Model model) {

        final List<ProductEntity> products = productService.findAll();

        model.addAttribute("products", products);
        return "view-stock";
    }

    @GetMapping("add")
    public String addStock(ProductEntity productEntity) {

        return "add-stock";
    }

    @PostMapping("add")
    public String addStock(@Valid ProductEntity productEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "add-stock";

        productService.save(productEntity);
        return "redirect:view-stock?add-stock=true";
    }
}