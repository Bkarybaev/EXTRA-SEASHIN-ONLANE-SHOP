import config.Validation;
import dao.UserDao;
import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
import enums.Category;
import enums.Role;
import enums.Size;
import exception.InvalidData;
import models.Product;
import models.User;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static User loginData;
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
                    loginData = login();
                    if (loginData.getEmail().equals(Admin.getEmail()) && loginData.getPassword().equals(Admin.getPassword())) {
                        aminMethod();
                    } else {
                        clientMethod();
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
        String email;
        boolean b;
        do {
            System.out.print("Write Email: ");
            email = scanner.nextLine();
            try {
                if (!Validation.checkEmail(email)) {
                    throw new InvalidData("Invalid email");
                }
                b = true;
            } catch (InvalidData e) {
                b = false;
                System.out.println(e.getMessage() + " try again!");
            }
        } while (!b);
        System.out.print("Write Password: ");
        String password = scanner.nextLine();
        boolean b1;
        do {
            try {
                if (!Validation.checkPassword(password)) {
                    throw new InvalidData("Invalid password");
                }
                b1 = true;
            } catch (InvalidData e) {
                b1 = false;
                System.out.println(e.getMessage() + " try again!");
            }
        } while (!b1);

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
        sizes = new Size[0];
        for (String string : s) {
            switch (string) {
                case "XXS" -> product.setSizes(size(Size.XXS));
                case "XS" -> product.setSizes(size(Size.XS));
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

    public static void aminMethod() {
        while (true) {
            System.out.print("""
                    1. Продукта кошуу.
                    2. Продукта очуруу.
                    3. Продукта озгортуу.
                    4. Бардык продукталарды коруу.
                    0. logout
                    """);
            System.out.print("write choice: ");
            int c = 0;
            boolean isblok1;
            do {
                try {
                    c = new Scanner(System.in).nextInt();
                    isblok1 = false;
                } catch (InputMismatchException e) {
                    isblok1 = true;
                    System.out.print("Invalid number try egan :");
                }
            } while (isblok1);
            switch (c) {
                case 1 -> productService.addProduct(addProduct());
                case 2 -> deletedProduct();
                case 3 -> updateByIdProduct();
                case 4 -> productService.getAllProduct();
                case 0 -> {
                    return;
                }
                default -> System.out.println("error choice!");
            }
        }
    }

    public static void deletedProduct() {
        while (true) {
            System.out.print("""
                    1.Deleted by id
                    2.Deleted all
                    0.logout
                    """);
            System.out.print("choice: ");
            int choice = 0;
            boolean isblock;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    isblock = false;
                } catch (InputMismatchException e) {
                    isblock = true;
                    System.out.print("choice try egan: ");
                }
            } while (isblock);
            switch (choice) {
                case 1 -> {
                    System.out.print("Deleted by id write number: ");
                    productService.deleteProductById(new Scanner(System.in).nextLong());
                }
                case 2 -> productService.deletedAllProduct();
                case 0 -> {
                    return;
                }
            }
        }
    }

    public static void updateByIdProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("write By Id update: ");
        long id = scanner.nextLong();
        Product product = addProduct();
        productService.updateById(id, product);
    }

    public static void clientMethod() {
        while (true) {
            System.out.print("""
                    1. Бардык продукталарды коруу.
                    2. Продукталарды категориясы менен размерлерине карап коруу.
                    3. Ар бир клиент каалган продуктаны id-си аркылуу, (favoriteProducts)
                    избранныйга кошуп кайра ал жактан очуро алуу.
                    4. Бир клиенттин бардык избранныйдагы продукталарын коруу.
                    5. Id-си аркылуу бир продуктаны коруу.
                    0. logout
                    """);
            System.out.print("write choice: ");
            int choice = 0;
            boolean isblok;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    isblok = false;
                } catch (InputMismatchException e) {
                    isblok = true;
                    System.out.println("Invalid choice try egan :");
                }
            } while (isblok);

            switch (choice) {
                case 1 -> productService.getAllProduct();
                case 2 -> category();
                case 3 -> favoriteProducts();
                case 4 -> getAllProductClientBasket();
                case 5 -> userService.getProductByIdClient(getProductByIdClientBasket(),loginData);
                case 0 -> {
                    return;
                }
            }
        }
    }

    public static void category() {
        while (true) {
            System.out.print("""
                    1.MAN
                    2.WOMAN
                    3.CHILDREN
                    0.logout
                    """);
            System.out.print("choice : ");
            int choice = 0;
            boolean isblok;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    isblok = false;
                } catch (InputMismatchException e) {
                    isblok = true;
                    System.out.println("error choice try egan :");
                }

            } while (isblok);
            System.out.print("write size [ XXL, XS, XL, S, M, XXL, L ]: ");
            String size = new Scanner(System.in).nextLine().toUpperCase();
            String[] arraySize = size.trim().split(" ");

            switch (choice) {
                case 1 -> {
                    for (Product product : productDao.getAllProduct()) {
                        if (product.getCategory().equals(Category.MAN)) {
                            for (int i = 0; i < arraySize.length; i++) {
                                for (int i1 = 0; i1 < product.getSizes().length; i1++) {
                                    if (arraySize[i].contains(product.getSizes()[i1])) {
                                        System.out.println(product);
                                    }
                                }
                            }
                        }
                    }
                }
                case 2 -> {
                    for (Product product : productDao.getAllProduct()) {
                        if (product.getCategory().equals(Category.WOMAN)) {
                            System.out.println(product);
                        }
                    }
                }
                case 3 -> {
                    for (Product product : productDao.getAllProduct()) {
                        if (product.getCategory().equals(Category.CHILDREN)) {
                            System.out.println(product);
                        }
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("error choice!!");
            }
        }
    }

    public static void favoriteProducts() {
        System.out.print("write by id: ");
        int id = 0;
        boolean b1;
        do {
            try {
                id = new Scanner(System.in).nextInt();
                b1 = false;
            } catch (Exception e) {
                b1 = true;
                System.out.println("try again: ");
            }
        } while (b1);
        userService.deletedByIdProductClient(id, productDao, loginData);
    }

    public static void getAllProductClientBasket() {
        System.out.println("-- Basket --");
        System.out.println(Arrays.toString(loginData.getBasket()));
    }

    public static int getProductByIdClientBasket() {
        System.out.print("write id: ");
        int id = 0;
        boolean b;
        do {
            try {
                id = new Scanner(System.in).nextInt();
                b = false;
            } catch (InputMismatchException e) {
                b = true;
                System.out.print("error write id: ");
            }
        } while (b);
        return id;
    }
}
