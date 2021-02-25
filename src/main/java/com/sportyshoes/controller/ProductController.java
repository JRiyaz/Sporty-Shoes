package com.sportyshoes.controller;

import com.sportyshoes.entity.ProductEntity;
import com.sportyshoes.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:all?add-product=true";
    }

    @GetMapping("view/{id}")
    public String viewProduct(@PathVariable Integer id, Model model) {

        final ProductEntity product = productService.findById(id).get();

        model.addAttribute("product", product);
        model.addAttribute("id", product.getId());
        return "view-product";
    }

    @PostMapping("update/{id}")
    public String updateProduct(@PathVariable Integer id, @Valid ProductEntity product, BindingResult result) {

        if (result.hasErrors()) {
//            product.setId(id);
            return "view-product";
        }

        productService.save(product);
        return "redirect:/product/all?product-update=true";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return "redirect:/product/all?delete-product=true";
    }
}
