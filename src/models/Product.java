package models;

import enums.Category;
import enums.Size;

import java.math.BigDecimal;
import java.util.Arrays;

public class Product {
    private long  id;
    private Category category;
    private String  name;
    private BigDecimal price;
    private Size[] sizes;
    private String  color;
    private String  imageUrl;
    private int quantity;

    //class
    public static long generateId = 1;

    //constructor
    public Product() {
        this.id = generateId;
    }

    public Product(Category category, String name, BigDecimal price, String color, String imageUrl, int quantity) {
        this.id = generateId++;
        this.category = category;
        this.name = name;
        this.price = price;
        this.color = color;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    //getter and setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Size[] getSizes() {
        return sizes;
    }

    public void setSizes(Size[] sizes) {
        this.sizes = sizes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sizes=" + Arrays.toString(sizes) +
                ", color='" + color + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                '}'+"\n";
    }
}
