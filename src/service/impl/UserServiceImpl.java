package service.impl;

import config.Validation;
import dao.UserDao;
import exception.InvalidData;
import models.Product;
import models.User;
import service.UserService;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String singUp(User user) {
        if (!Validation.checkEmail(user.getEmail())){
            throw new InvalidData("Invalid Email");
        }else {
            User[] users = userDao.findAll();
            for (User u : users) {
                if (u.getEmail().equals(user.getEmail())){
                    throw new IllegalArgumentException("similar email!");
                }
            }

        }
        if (!Validation.checkPassword(user.getPassword())){
            throw new InvalidData("Invalid Password!!");
        }
        userDao.save(user);
        return "successful!!";
    }

    @Override
    public User login(String email, String password) {
        User[] users = userDao.findAll();
        boolean isblok = false;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                isblok = true;
                return user;
            }
        }
        if (!isblok) System.out.println("not fount !");
        return null;
    }


}
