package kris.spring.mvc.controllers;

import kris.spring.mvc.exceptions.NoSuchProductException;
import kris.spring.mvc.models.Product;
import kris.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private Product foundProduct;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("prod")
    public String showProductsPage(Model model) {
        List<Product> list = productService.getAllProducts();
        model.addAttribute("products", list);
        return "products_table";
    }

    @PostMapping("add")
    public String addProduct(@RequestParam String title, @RequestParam int cost) {
        Product product = new Product(productService.getNextID(), title, cost);
        productService.add(product);
        return "redirect:/prod";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteByID(id);
        return "redirect:/prod";
    }

    @PostMapping("/find")
    public String findProduct(@RequestParam long id) {
        try {
            foundProduct = productService.getProductByID(id);

        } catch (NoSuchProductException e) {
            foundProduct = new Product(0, "Not Found Product with id = " + id, 0); //не очень красиво
        }
        return "redirect:/find_prod";
    }

    @GetMapping("find_prod")
    public String showFoundProductPage(Model model) {
        List<Product> list = new ArrayList<>();
        list.add(foundProduct);
        model.addAttribute("products", list);
        return "found_product";
    }
}
