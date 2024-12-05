package service.impl;
import config.Validation;
import dao.UserDao;
import dao.impl.ProductDaoImpl;
import exception.InvalidData;
import models.Product;
import models.User;
import service.UserService;

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

    @Override
    public void deletedByIdProductClient(int id, ProductDaoImpl productDao, User loginData) {
       if (id<1){
           throw new RuntimeException("id lower zero!!");
       }else {
           for (Product product : productDao.getAllProduct()) {
              if (product.getId() == id){
                  userDao.deletedByIdProductClient(id,productDao,loginData);
              }else {
                  throw new RuntimeException("not fount " + id + " id !!");
              }
           }
       }
    }


    public void getProductByIdClient(int id, User loginData) {
        boolean isblock = false;
        for (Product p : loginData.getBasket()) {
            if (p.getId() == id) {
                isblock = true;
                userDao.getProductByIdClient(id,loginData);
            }
        }
        if (!isblock) System.out.println("not fount " + id + " product!!");
    }
}
