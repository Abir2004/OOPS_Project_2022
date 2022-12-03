import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws Exception {
        startapp();
    }

    public static void startapp() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 1 for Admin Mode, Enter 2 for User Mode, Enter 3 to Exit");
        String s = in.nextLine().trim();
        if (s.equals("1")) {
            adminLogin();
        } else if (s.equals("2")) {
            System.out.println("Enter 1 to Login, Enter 2 to Register");
            String ch = in.nextLine().trim();
            if (ch.equals("1")) {
                userLogin();
            } else if (ch.equals("2")) {
                userRegister();
            }
        } else if (s.equals("3")) {
        } else {
            System.out.println("Invalid Option. Program Terminating!!!");
        }
    }

    public static void adminLogin() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your UserID and Password ->");
        System.out.print("UserID : ");
        String user = in.nextLine().trim();
        System.out.print("Password: ");
        String pwd = in.nextLine().trim();
        boolean can = Admin.Verify(user, pwd);
        if (can) {
            Admin admin = new Admin(user, pwd);
            Thread thread = new Thread(admin);
            thread.start();
            Thread.currentThread().interrupt();
        } else {
            System.out.println("Invalid Credentials!");
            adminLogin();
        }
    }

    public static void userLogin() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your UserID and Password ->");
        System.out.print("UserID : ");
        String user1 = in.nextLine().trim();
        System.out.print("Password: ");
        String pwd = in.nextLine().trim();
        boolean can = User.Verify(user1, pwd);
        if (can) {
            User user = new User(user1, pwd);
            Thread thread = new Thread(user);
            thread.start();
            Thread.currentThread().interrupt();
        } else {
            System.out.println("Invalid Credentials!");
            userLogin();
        }
    }

    public static void userRegister() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your Details below : ");
        System.out.print("Email-ID : ");
        String user1 = in.nextLine().trim();
        System.out.print("Password: ");
        String pwd = in.nextLine().trim();
        System.out.print("Name: ");
        String name = in.nextLine().trim();
        System.out.print("BITS-ID: ");
        String ID = in.nextLine().trim();
        System.out.println("Registered Successfully!");
        User user = new User(user1, pwd, name, ID);
        Thread thread = new Thread(user);
        thread.start();
        Thread.currentThread().interrupt();
    }
}
