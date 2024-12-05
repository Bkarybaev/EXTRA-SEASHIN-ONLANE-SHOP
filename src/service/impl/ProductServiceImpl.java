package service.impl;
import dao.impl.ProductDaoImpl;
import exception.InvalidData;
import models.Product;
import service.ProductService;
import java.math.BigDecimal;
import java.util.Arrays;

public class ProductServiceImpl implements ProductService {
     private final ProductDaoImpl productDao;

    public ProductServiceImpl(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }
    @Override
    public void addProduct(Product product) {
        try {
            if (product.getName() == null){
                throw new InvalidData("Product name null!!");
            }
            if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0){
                throw new InvalidData("try egan price !");
            }
            productDao.addProduct(product);
            System.out.println("Successful add product!!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteProductById(long id) {
        if (id < 1){
            throw new InvalidData("id null try egan!");
        }
        productDao.deleteProductById(id);
    }

    @Override
    public void deletedAllProduct() {
        productDao.deletedAllProduct();
    }

    @Override
    public void updateById(long id, Product newProduct) {
        try {
            if (newProduct.getName() == null){
                throw new InvalidData("Product name null!!");
            }
            if (newProduct.getPrice().compareTo(BigDecimal.ZERO) <= 0){
                throw new InvalidData("try egan price !");
            }
            productDao.updateById(id,newProduct);
            System.out.println("Successful update product!!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getAllProduct() {
        System.out.println(Arrays.toString(productDao.getAllProduct()));
    }
}
