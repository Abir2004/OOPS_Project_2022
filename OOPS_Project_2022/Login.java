import java.util.*;
import java.io.*;

public class Login {

    public void Register(String s1, String s2, String s3) throws Exception {
        PrintWriter output = new PrintWriter(new FileWriter(s3, true));
        output.println(s1 + ":" + s2);
        output.close();
    }

    public void Register(String s1, String s2, String s3, String s4, String s5) throws Exception {
        PrintWriter output = new PrintWriter(new FileWriter(s5, true));
        output.println(s1 + ":" + s2 + ":" + s3 + ":" + s4);
        output.close();
    }

    public static boolean Verify(String s1, String s2, String s3) throws Exception {
        Scanner input = new Scanner(new FileReader(s3));
        boolean can = false;
        while (input.hasNextLine()) {
            String s = input.nextLine().trim();
            String[] arr = s.split(":");
            if (arr[0].equals(s1) && arr[1].equals(s2)) {
                can = true;
                break;
            }
        }
        return can;
    }
}