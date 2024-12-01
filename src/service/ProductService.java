package service;

import models.Product;

public interface ProductService {
    void addProduct(Product product);

    void deleteProductById(long id);

    void deletedAllProduct();

    void updateById(long id, Product newProduct);

    void getAllProduct();



}
