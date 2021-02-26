package com.sportyshoes.controller;

import com.sportyshoes.entity.OrderEntity;
import com.sportyshoes.entity.ProductEntity;
import com.sportyshoes.entity.UserEntity;
import com.sportyshoes.service.OrderService;
import com.sportyshoes.service.ProductService;
import com.sportyshoes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

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

   /* @GetMapping("view/{suitableFor}/{id}")
    public String viewSuitableShoes(@PathVariable String suitableFor, @PathVariable Integer id, Model model) {

        suitableFor = StringUtils.capitalize(suitableFor);

        System.out.println("First one called");

        ProductEntity product = new ProductEntity();

        if (suitableFor.equalsIgnoreCase("brand"))
            product = productService.findById(id).get();
        else
            product = productService.findByIdAndSuitableFor(suitableFor, id);

        model.addAttribute("product", product);
        model.addAttribute("id", id);

        return "buy-product";
    }*/

    @GetMapping("view/{suitableFor}/{type}/{id}")
    public String viewSuitableAndTypeShoes(@PathVariable String suitableFor,
                                           @PathVariable String type,
                                           @PathVariable Integer id,
                                           Model model) {

        suitableFor = StringUtils.capitalize(suitableFor);
        type = StringUtils.capitalize(type);

        ProductEntity product = new ProductEntity();

        if (suitableFor.equalsIgnoreCase("brand"))
            product = productService.findByIdAndBrand(type, id);
        else
            product = productService.findByIdAndSuitableForAndType(suitableFor, type, id);

        model.addAttribute("product", product);
        model.addAttribute("id", id);

        return "buy-product";
    }

    @GetMapping("all/{suitableFor}")
    public String allShoes(@PathVariable String suitableFor, Model model) {

        suitableFor = StringUtils.capitalize(suitableFor);

        String heading = suitableFor + " Shoes";
        String title = heading + " | Sporty Shoes";

        List<ProductEntity> productEntities = new ArrayList<>();

        if (suitableFor.equalsIgnoreCase("brand")) {
            productEntities = productService.findAll();
            heading = "All " + heading;
            title = "All " + title;
        } else
            productEntities = productService.findAllBySuitableFor(suitableFor);

        model.addAttribute("suitableFor", suitableFor);
        model.addAttribute("title", title);
        model.addAttribute("heading", heading);
        model.addAttribute("products", productEntities);

        return "all-products";
    }

    @GetMapping("all/{suitableFor}/{type}")
    public String allShoeTypes(@PathVariable String suitableFor, @PathVariable String type, Model model) {

        suitableFor = StringUtils.capitalize(suitableFor);
        type = StringUtils.capitalize(type);

        String heading = suitableFor + " " + type + " Shoes";
        String title = heading + " | Sporty Shoes";

        List<ProductEntity> productEntities = new ArrayList<>();

        if (suitableFor.equalsIgnoreCase("brand")) {
            productEntities = productService.findByBrand(type);
            heading = type + " Shoes";
            title = heading + " | Sporty Shoes";
        } else
            productEntities = productService.findAllBySuitableForAndType(suitableFor, type);

        model.addAttribute("suitableFor", suitableFor);
        model.addAttribute("title", title);
        model.addAttribute("heading", heading);
        model.addAttribute("products", productEntities);

        return "all-products";
    }

    @GetMapping("/buy/{id}")
    public String buyShoes(@PathVariable Integer id, HttpServletRequest request) throws InterruptedException {

        final ProductEntity productEntity = productService.findById(id).get();
        final OrderEntity orderEntity = new OrderEntity(productEntity.getPrice(), true, Arrays.asList(productEntity), userService.findById(1).get());
        orderService.save(orderEntity);
        final String uri = request.getHeader("Referer");

        return "redirect:" + uri + "?order=true";
    }
}
