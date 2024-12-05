package dao;
import models.Product;

public interface ProductDao {
    void addProduct(Product product);

    void deleteProductById(long id);

    void deletedAllProduct();

    void updateById(long id, Product newProduct);

    Product[] getAllProduct();



}
