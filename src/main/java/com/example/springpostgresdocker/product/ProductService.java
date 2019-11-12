package com.example.springpostgresdocker.product;

public interface ProductService {

	Product getProduct(String productName);

	Product createProduct(String name);
}
