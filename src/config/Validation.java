package config;

public class Validation {
    public static boolean checkEmail(String email){
        String pattern = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(pattern);
    }
    public static boolean checkPassword(String password){
        String pattern ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$&!=#+]).{8,}$";
        return password.matches(pattern);

    }
}
