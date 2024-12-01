package service.impl;

import dao.impl.ProductDaoImpl;
import exception.InvalidData;
import models.Product;
import service.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {
     private final ProductDaoImpl productDao;

    public ProductServiceImpl(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }
    @Override
    public void addProduct(Product product) {
        try {
            if (product.getName() == null){
                throw new InvalidData("Product name equals null!!");
            }
            if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0){
                throw new InvalidData("try egan price !");
            }
            productDao.addProduct(product);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
