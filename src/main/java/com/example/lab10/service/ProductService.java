package com.example.lab10.service;

import org.springframework.stereotype.Service;

import com.example.lab10.model.Product;
import com.example.lab10.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Mono<Product> getById(String id) {
        return repository.findById(id);
    }

    public Flux<Product> getAll() {
        return repository.findAll();
    }

    public Mono<Product> create(Product product) {
        return repository.save(product);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    public Flux<Product> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    public Mono<Double> getPriceById(String id) {
        return repository.findById(id)
                .map(Product::getPrice);
    }
}