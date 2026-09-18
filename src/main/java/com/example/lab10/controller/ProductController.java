package com.example.lab10.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab10.model.Product;
import com.example.lab10.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET /products/{id} -> Mono<Product> (ตัวอย่างเดิม)
    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable String id) {
        return service.getById(id);
    }

    // GET /products -> Flux<Product>
    @GetMapping
    public Flux<Product> getAll() {
        return service.getAll();
    }

    // POST /products -> Mono<Product>
    @PostMapping
    public Mono<Product> create(@RequestBody Product product) {
        return service.create(product);
    }

    // DELETE /products/{id} -> Mono<Void>
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    // GET /products/category/{cat} -> Flux<Product>
    @GetMapping("/category/{cat}")
    public Flux<Product> getByCategory(@PathVariable("cat") String category) {
        return service.getByCategory(category);
    }

    // GET /products/{id}/price -> Mono<Double>
    @GetMapping("/{id}/price")
    public Mono<Double> getPrice(@PathVariable String id) {
        return service.getPriceById(id);
    }
}