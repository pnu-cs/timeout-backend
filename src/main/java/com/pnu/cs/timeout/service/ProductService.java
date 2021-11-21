package com.pnu.cs.timeout.service;

import com.pnu.cs.timeout.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product readById(long id);
    Product update(Product product);
    void delete(long id);
    List<Product> getAll();
}
