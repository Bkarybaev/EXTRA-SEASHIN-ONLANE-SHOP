package dao.impl;

import dao.ProductDao;
import db.DataBase;
import models.Product;

import java.util.Arrays;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void addProduct(Product product) {
        DataBase.products = Arrays.copyOf(DataBase.products, DataBase.products.length + 1);
        DataBase.products[DataBase.products.length - 1] = product;
        System.out.println(Arrays.toString(DataBase.products));
    }
}
