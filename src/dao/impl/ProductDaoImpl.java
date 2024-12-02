package dao.impl;
import dao.ProductDao;
import db.DataBase;
import models.Product;
import java.util.Arrays;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void addProduct(Product product) {
        Product.generateId += 1;
        DataBase.products = Arrays.copyOf(DataBase.products, DataBase.products.length + 1);
        DataBase.products[DataBase.products.length - 1] = product;
        System.out.println(Arrays.toString(DataBase.products));
    }

    @Override
    public void deleteProductById(long id) {
        boolean isblok = false;
        for (int i = 0; i < DataBase.products.length; i++) {
            if (id == DataBase.products[i].getId()) {
                isblok = true;
                for (int i1 = i; i1 < DataBase.products.length-1; i1++) {
                    DataBase.products[i1] = DataBase.products[i1 + 1];
                    System.out.println("deleted" + id + " !!");
                }
            }
        }
        DataBase.products = Arrays.copyOf(DataBase.products, DataBase.products.length - 1);
        if (!isblok) System.out.println("not fount " + id + " id !");
    }

    @Override
    public void deletedAllProduct() {
        DataBase.products = new Product[0];
        System.out.println("Deleted all product!!");
    }

    @Override
    public void updateById(long id,Product newProduct) {
        boolean isblok = false;
        for (Product product : DataBase.products) {
            if (product.getId() == id) {
                isblok = true;
                product.setId(id);
                product.setCategory(newProduct.getCategory());
                product.setName(newProduct.getName());
                product.setPrice(newProduct.getPrice());
                product.setSizes(newProduct.getSizes());
                product.setColor(newProduct.getColor());
                product.setImageUrl(newProduct.getImageUrl());
                product.setQuantity(newProduct.getQuantity());
                System.out.println(product);
            }
        }
        if (!isblok) System.out.println("not fount " + id + "id !");
    }

    @Override
    public Product[] getAllProduct() {
        return DataBase.products;
    }
}
