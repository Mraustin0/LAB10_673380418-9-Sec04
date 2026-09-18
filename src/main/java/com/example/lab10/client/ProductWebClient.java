package com.example.lab10.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.lab10.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductWebClient {

    private final WebClient webClient;

    public ProductWebClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080").build();
    }

    // 1. GET /products/{id}
    public Mono<Product> getProductById(String id) {
        return webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }

    // 2. GET /products
    public Flux<Product> getAllProducts() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }

    // 3. POST /products
    public Mono<Product> createProduct(Product product) {
        return webClient.post()
                .uri("/products")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    // 4. DELETE /products/{id}
    public Mono<Void> deleteProduct(String id) {
        return webClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // 5. GET /products/category/{cat}
    public Flux<Product> getProductsByCategory(String category) {
        return webClient.get()
                .uri("/products/category/{cat}", category)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    // 6. GET /products/{id}/price
    public Mono<Double> getProductPrice(String id) {
        return webClient.get()
                .uri("/products/{id}/price", id)
                .retrieve()
                .bodyToMono(Double.class);
    }
}