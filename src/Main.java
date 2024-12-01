import config.Validation;
import dao.UserDao;
import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
import db.DataBase;
import enums.Category;
import enums.Role;
import enums.Size;
import exception.InvalidData;
import models.Product;
import models.User;
import service.ProductService;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Size[] sizes = new Size[0];
    static User Admin = new User("admin@gmail.com", "Admin123!", "Admin", Role.ADMIN);
    static ProductDaoImpl productDao = new ProductDaoImpl();
    static UserDao userDao = new UserDaoImpl();
    static ProductServiceImpl productService = new ProductServiceImpl(productDao);
    static UserServiceImpl userService = new UserServiceImpl(userDao);

    public static void main(String[] args) {
        while (true) {
            int chose = 0;
            System.out.print("""
                    1.registration
                    2.login
                    """);
            System.out.print("write number:");
            boolean isblok;
            do {
                try {
                    chose = new Scanner(System.in).nextInt();
                    isblok = false;

                } catch (InputMismatchException e) {
                    isblok = true;
                    System.out.print("Invalid number try egan :");
                }
            } while (isblok);

            switch (chose) {
                case 1 -> {
                    registration();
                }
                case 2 -> {
                    User loginData = login();
                    if (loginData.getEmail().equals(Admin.getEmail()) && loginData.getPassword().equals(Admin.getPassword())) {
                        productService.addProduct(addProduct());
                    }
                }
            }
        }
    }

    public static void registration() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.print("Write name: ");
        user.setName(scanner.nextLine());

        System.out.print("Write email: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Write password: ");
        user.setPassword(scanner.nextLine());

        if (!user.getEmail().equals(Admin.getEmail()) && !user.getPassword().equals(Admin.getPassword())) {
            user.setUser(Role.CLIENT);
        } else {
            System.out.println("password and email refusal try egan!");
        }
        userService.singUp(user);
    }

    public static User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write Email: ");
        String email = scanner.nextLine();
        if (!Validation.checkEmail(email)) {
            throw new InvalidData("Invalid email");
        }
        System.out.print("Write Password: ");
        String password = scanner.nextLine();
        if (!Validation.checkPassword(password)) {
            throw new InvalidData("Invalid password");
        }
        if (email.equals(Admin.getEmail()) && password.equals(Admin.getPassword())) {
            return Admin;
        }
        return userService.login(email, password);

    }

    public static Product addProduct() {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        int chose = 0;
        System.out.print("""
                write category
                1.MAN
                2.WOMAN
                3.CHILDREN
                """);
        System.out.print("write choice:");
        boolean isblok;
        do {
            try {
                chose = new Scanner(System.in).nextInt();
                isblok = false;

            } catch (InputMismatchException e) {
                isblok = true;
                System.out.print("Invalid number try egan :");
            }
        } while (isblok);

        switch (chose) {
            case 1 -> {
                product.setCategory(Category.MAN);
            }
            case 2 -> {
                product.setCategory(Category.WOMAN);
            }
            case 3 -> {
                product.setCategory(Category.CHILDREN);
            }
            default -> System.out.println("try egan choice!");
        }

        System.out.print("write name: ");
        product.setName(scanner.nextLine());

        System.out.print("write price (0,00): ");
        product.setPrice(new Scanner(System.in).nextBigDecimal());

        System.out.print("""
                write size
                [ XXL, XS, XL, S, M, XXL, L ]
                """);
        System.out.print("write choice:");
        String size = scanner.nextLine().toUpperCase();
        String[] s = size.trim().split(" ");
        for (String string : s) {
            switch (string) {
                case "XXS" -> product.setSizes(size(Size.XXS));
                case "XS" ->product.setSizes(size(Size.XS)) ;
                case "XL" -> product.setSizes(size(Size.XL));
                case "S" -> product.setSizes(size(Size.S));
                case "M" -> product.setSizes(size(Size.M));
                case "XXL" -> product.setSizes(size(Size.XXL));
                case "L" -> product.setSizes(size(Size.L));
                default -> System.out.println("error choice!");
            }
        }
        System.out.print("write color: ");
        product.setColor(scanner.nextLine());

        System.out.print("write imageUrl: ");
        product.setImageUrl(scanner.nextLine());

        System.out.print("write quantity: ");
        product.setQuantity(new Scanner(System.in).nextInt());
        return product;
    }

    public static Size[] size(Size size) {
        sizes = Arrays.copyOf(sizes, sizes.length + 1);
        sizes[sizes.length - 1] = size;
        return sizes;
    }
}
