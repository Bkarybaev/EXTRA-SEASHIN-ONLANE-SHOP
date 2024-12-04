package dao;

import dao.impl.ProductDaoImpl;
import models.Product;
import models.User;

public interface UserDao {
    void save(User user);

    User[] findAll();

    void deletedByIdProductClient(int id , ProductDaoImpl productDao, User loginData);



}
