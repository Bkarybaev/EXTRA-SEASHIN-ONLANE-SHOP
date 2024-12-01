package dao.impl;

import dao.UserDao;
import db.DataBase;
import models.Product;
import models.User;

import java.util.Arrays;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        DataBase.users = Arrays.copyOf(DataBase.users , DataBase.users.length + 1);
        DataBase.users[DataBase.users.length - 1] = user;
        System.out.println(user);
        System.out.println("successful user!!");
    }

    @Override
    public User[] findAll() {
        return DataBase.users;
    }


}
