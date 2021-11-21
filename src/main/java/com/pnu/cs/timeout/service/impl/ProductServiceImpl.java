package com.pnu.cs.timeout.service.impl;

import com.pnu.cs.timeout.model.Product;
import com.pnu.cs.timeout.repository.ProductRepository;
import com.pnu.cs.timeout.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        if (product != null) {
            productRepository.save(product);
        }
        return product;
    }

    @Override
    public Product readById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        if (product != null) {
            if (productRepository.existsById(product.getId())) {
                return productRepository.save(product);
            }
        }

        return null;
    }

    @Override
    public void delete(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        return products.isEmpty() ? new ArrayList<>() : products;
    }
}
