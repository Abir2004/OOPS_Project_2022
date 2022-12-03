import java.util.*;
import java.io.*;

public class Room {
    private String RoomName;
    private String RoomFile;
    private boolean Validity;

    public Room(String RoomName, int reqSeats) throws Exception {
        this.RoomName = RoomName;
        this.RoomFile = RoomName + ".txt";
        Scanner input = new Scanner(new FileReader(this.RoomFile));
        boolean can = false;
        while (input.hasNextLine()) {
            String s = input.nextLine().trim();
            String[] arr = s.split(":");
            if (arr[0].equals("Capacity")) {
                if (Integer.parseInt(arr[1]) < reqSeats) {
                    throw new LargerRoomException("Room is not of required size.");
                } else {
                    can = true;
                }
                break;
            }
        }
        if (can) {
            this.Validity = true;
        } else {
            this.Validity = false;
        }
    }

    public boolean getValidity() {
        return this.Validity;
    }

    public String toString() {
        return this.RoomName;
    }

    public static void showRooms() throws Exception {
        ArrayList<String> booked = new ArrayList<String>();
        ArrayList<String> maybebooked = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        boolean found = false;
        String code = "";
        while (!found) {
            System.out.print("Enter the Room Code : ");
            code = in.nextLine().trim();
            Scanner input = new Scanner(new FileReader("RoomList.txt"));
            while (input.hasNextLine()) {
                String s1 = input.nextLine().trim();
                if (s1.equals(code)) {
                    found = true;
                    break;
                }
            }
        }
        System.out.print("Date (DD/MM/YYYY): ");
        String Date1 = in.nextLine().trim();
        Scanner input = new Scanner(new FileReader("Application.txt"));
        while (input.hasNextLine()) {
            String s = input.nextLine().trim();
            String[] arr = s.split(";");
            if (arr[5].equals(Date1) && arr[8].equals(code))
                if (arr[arr.length - 2].equals("Status")) {
                    if (arr[arr.length - 1].equals("True")) {
                        booked.add(arr[arr.length - 3]);
                    }
                } else {
                    String s1 = arr[6];
                    s1 = s1.substring(1, s1.length() - 1);
                    String[] arr1 = s1.split(",");
                    for (int i = 0; i < arr1.length; i++) {
                        String s3 = arr1[i].trim();
                        maybebooked.add(Date.TimeProcess(s3));
                    }
                }
        }
        ArrayList<String> free = new ArrayList<String>();
        String start = "08:00";
        Collections.sort(booked);
        for (int i = 0; i < booked.size(); i++) {
            free.add(start + " to " + booked.get(i).substring(0, 5));
            start = booked.get(i).substring(9, 14);
        }
        free.add(start + " to 23:00");
        System.out.print("Free Timings : ");
        for (int i = 0; i < free.size(); i++) {
            System.out.print(free.get(i));
            if (i < free.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.print("Booking Timings Waiting for Approval: ");
        for (int i = 0; i < maybebooked.size(); i++) {
            System.out.print(maybebooked.get(i));
            if (i < maybebooked.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
