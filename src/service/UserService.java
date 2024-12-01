package service;

import models.Product;
import models.User;

public interface UserService {
    String singUp(User user);

    User login(String email,String password);
}
