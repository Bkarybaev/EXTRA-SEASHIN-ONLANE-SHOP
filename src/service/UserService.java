package service;

import dao.impl.ProductDaoImpl;
import models.Product;
import models.User;

public interface UserService {
    String singUp(User user);

    User login(String email,String password);

    void deletedByIdProductClient(int id , ProductDaoImpl productDao, User loginData);
}
