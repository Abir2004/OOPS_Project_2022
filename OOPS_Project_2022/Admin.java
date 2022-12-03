import java.util.*;
import java.lang.*;
import java.io.*;

public class Admin extends Login implements Runnable {
    private static String adminio = "Admin.txt";
    private String Email_ID;
    private String Passwd;

    public void run() {
        try {
            forward();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void forward() throws Exception{
        System.out.println("Admin Mode");
        Scanner in = new Scanner(System.in);
        System.out.println("Press 1 to View Applications.");
        String s = in.nextLine().trim();
        if (s.equals("1")) {
            AppliInfo();
        }
    }

    public Admin(String s1, String s2) throws Exception {
        this.Email_ID = s1;
        this.Passwd = s2;
    }

    public static boolean Verify(String s1, String s2) throws Exception {
        boolean can = Admin.Verify(s1, s2, adminio);
        return can;
    }

    public void AppliInfo() throws Exception{
        Application.AppliInfo();
    }
}