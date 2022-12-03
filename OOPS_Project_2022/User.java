import java.util.*;

import javax.lang.model.util.ElementScanner14;

import java.lang.*;
import java.io.*;

public class User extends Login implements Runnable {
    private static String userio = "User.txt";
    private static String appuserio = "UserApplication.txt";
    private String Email_ID;
    private String Passwd;
    private String Name;
    private String BITS_ID;

    public void run() {
        try {
            forward();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void forward() throws Exception {
        System.out.println("User Mode");
        Scanner in = new Scanner(System.in);
        System.out.println("Press 1 to View Rooms.");
        System.out.println("Press 2 to Book a Room.");
        System.out.println("Press 3 to Track Booking.");
        System.out.println("Press 4 to Cancel Booking.");
        String s = in.nextLine().trim();
        if (s.equals("1")) {
            RoomInfo();
        } else if (s.equals("2")) {
            MakeApplication();
        } else if (s.equals("3")) {
            TrackApplication();
        } else if (s.equals("4")) {
            CancelBooking();
        }
    }

    public User(String Email, String Pwd, String name, String ID) throws Exception {
        this.Register(Email, Pwd, name, ID, userio);
        this.Email_ID = Email;
        this.Passwd = Pwd;
        this.Name = name;
        this.BITS_ID = ID;
    }

    public User(String Email, String Pwd) throws Exception {
        this.Email_ID = Email;
        this.Passwd = Pwd;
        Scanner input1 = new Scanner(new FileReader(userio));
        while (input1.hasNextLine()) {
            String s = input1.nextLine().trim();
            String[] arr = s.split(":");
            if (arr[0].equals(Email) && arr[1].equals(Pwd)) {
                this.Name = arr[2];
                this.BITS_ID = arr[3];
                break;
            }
        }
    }

    public String getID() {
        return this.BITS_ID;
    }

    public static boolean Verify(String s1, String s2) throws Exception {
        boolean can = User.Verify(s1, s2, userio);
        return can;
    }

    public synchronized void RoomInfo() throws Exception {
        Room.showRooms();
    }

    public void MakeApplication() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Application Details below : ");
        System.out.print("Name : ");
        String Name = in.nextLine().trim();
        System.out.print("Organisation: ");
        String Org = in.nextLine().trim();
        System.out.print("BITS-ID: ");
        String ID = in.nextLine().trim();
        System.out.print("Reason of Booking: ");
        String Reason = in.nextLine().trim();
        System.out.print("Time Slots (Give Comma Separated Slots in 24 Hr Format ABCD-WXYZ: ");
        String timer = in.nextLine().trim();
        String[] TimeSlots = timer.split(",");
        System.out.print("Date (DD/MM/YYYY): ");
        String Date1 = in.nextLine().trim();
        String[] arr = Date1.split("/");
        Date date = new Date(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        System.out.print("Number of Students: ");
        int students = Integer.parseInt(in.nextLine().trim());
        System.out.print("Room Code: ");
        String room1 = in.nextLine().trim();
        Room room;
        try {
            room = new Room(room1, students);
        } catch (LargerRoomException e) {
            System.out.println(e.getMessage());
            System.out.print("Room Code: ");
            String room2 = in.nextLine().trim();
            room = new Room(room2, students);
        }
        Application appl;
        synchronized (this) {
            if (Org == null) {
                appl = new Application(Name, ID, Reason, date, TimeSlots, students, room);
            } else {
                appl = new Application(Name, Org, ID, Reason, date, TimeSlots, students, room);
            }
        }
    }

    public void TrackApplication() throws Exception {
        Application appl = new Application();
        appl.BookingDetails();
    }

    public void CancelBooking() throws Exception {
        Application appl = new Application();
        appl.cancelBooking();
    }

    public void addMoreInfo(int appno, String Moreinfo) throws Exception {
        PrintWriter output = new PrintWriter(new FileWriter("Application.txt", true));
        output.println(Integer.toString(appno) + ":MoreInfo:" + Moreinfo);
        output.close();
    }

}