package models;

import enums.Role;

import java.util.Arrays;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private Role user;
    private Product[] basket = new Product[0];

    //class
    public static long generateId = 1;

    //constructor

    public User() {
        this.id = generateId;
    }

    public User(String email, String password, String name, Role user) {
        this.id = generateId++;
        this.email = email;
        this.password = password;
        this.name = name;
        this.user = user;
    }

    //getter and setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getUser() {
        return user;
    }

    public void setUser(Role user) {
        this.user = user;
    }

    public Product[] getBasket() {
        return basket;
    }

    public void setBasket(Product[] basket) {
        this.basket = basket;
    }

    public void addBasket(Product product) {
        this.basket = Arrays.copyOf(basket, basket.length + 1);
        this.basket[basket.length - 1] = product;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
