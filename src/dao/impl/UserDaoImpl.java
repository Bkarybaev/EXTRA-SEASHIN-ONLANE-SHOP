package dao.impl;
import dao.UserDao;
import db.DataBase;
import models.Product;
import models.User;

import java.util.Arrays;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        User.generateId += 1;
        DataBase.users = Arrays.copyOf(DataBase.users , DataBase.users.length + 1);
        DataBase.users[DataBase.users.length - 1] = user;
        System.out.println(user);
        System.out.println("successful user!!");
    }

    @Override
    public User[] findAll() {
        return DataBase.users;
    }

    @Override
    public void deletedByIdProductClient(int id, ProductDaoImpl productDao, User loginData) {
        for (Product product : productDao.getAllProduct()) {
            long l = 0;
            for (Product p : loginData.getBasket()) {
                if (p.getId() == id)l = p.getId();
            }
            if (l == id) {
                for (int i = 0; i < loginData.getBasket().length; i++) {
                    if (id == loginData.getId()) {
                        for (int i1 = i; i1 < loginData.getBasket().length-1; i1++) {
                            loginData.getBasket()[i1] = loginData.getBasket()[i1 + 1];
                        }
                        System.out.println("deleted product in [basket]!!");
                    }
                }
                loginData.setBasket(Arrays.copyOf(loginData.getBasket(), loginData.getBasket().length - 1));
            } else {
                loginData.addBasket(product);
                System.out.println("successful product [basket]");
                System.out.println(loginData);
            }
        }
    }




}
