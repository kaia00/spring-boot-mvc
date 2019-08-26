package com.springbootmvc.tutorials.demo.springBootMVC.controller;

import com.springbootmvc.tutorials.demo.springBootMVC.domain.Product;
import com.springbootmvc.tutorials.demo.springBootMVC.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String showProductList(Product product){
        return "add-product";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-product";
        }
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "main";
    }

    @GetMapping("/deleteProduct/{id}") // DOESN'T WORK. Calls /delete/{id}
    public String deleteProduct(@PathVariable("id") long id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("This product ID doesn't exist: " + id));
        productRepository.delete(product);
        model.addAttribute("products", productRepository.findAll());
        return "main";
    }

    @GetMapping("/editProduct/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid product id" + id));
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result, Model model){
        if(result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "main";
    }

}
