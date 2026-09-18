package com.example.lab10.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.lab10.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepository {

    private final Map<String, Product> database = new ConcurrentHashMap<>();

    public ProductRepository() {
        // ใส่พารามิเตอร์ให้ครบ 7 ตัว: (id, name, category, brand, stock, price, discountType)
        database.put("1", new Product("1", "Laptop (673380418-9 นายภควัฒน์ สุขมณี)", "Electronics", "Apple", 10, 31500.0, "MEMBER"));
        database.put("2", new Product("2", "Mouse", "Electronics", "Logitech", 50, 850.0, "NONE"));
        database.put("3", new Product("3", "Coffee Mug", "Home", "IKEA", 20, 250.0, "SEASONAL"));
    }

    public Mono<Product> findById(String id) {
        return Mono.justOrEmpty(database.get(id));
    }

    public Flux<Product> findAll() {
        return Flux.fromIterable(database.values());
    }

    public Mono<Product> save(Product product) {
        database.put(product.getId(), product);
        return Mono.just(product);
    }

    public Mono<Void> deleteById(String id) {
        database.remove(id);
        return Mono.empty();
    }

    public Flux<Product> findByCategory(String category) {
        return Flux.fromIterable(database.values())
                .filter(product -> product.getCategory().equalsIgnoreCase(category));
    }
}