import java.io.*;
import java.util.*;

public class Application {
    private static String applio = "Application.txt";
    private static String appnio = "AppNumber.txt";
    private static String trio = "Transition.txt";
    private static int number;
    private String finalTime;
    private int applicationNumber;
    private boolean Status;
    private String Name;
    private String Org = "";
    private String ID;
    private String Reason;
    private Date date;
    private String[] TimeSlots;
    private int students;
    private Room room;
    private String moreInfo = "";
    private boolean moreReq = false;

    public Application(String Name, String ID, String Reason, Date date, String[] TimeSlots,
            int students, Room room) throws Exception {
        int number = 0;
        this.Name = Name;
        this.Org = null;
        this.ID = ID;
        this.Reason = Reason;
        this.date = date;
        this.TimeSlots = TimeSlots;
        this.students = students;
        this.Status = false;
        this.room = room;
        Scanner input = new Scanner(new FileReader("AppNumber.txt"));
        while (input.hasNextLine()) {
            number = Integer.parseInt(input.nextLine().trim());
        }
        number++;
        PrintWriter output1 = new PrintWriter(new FileWriter("AppNumber.txt", false));
        output1.println(Integer.toString(number));
        output1.close();
        PrintWriter output = new PrintWriter(new FileWriter("Application.txt", true));
        output.println(Integer.toString(number) + ";" + Name + ";" + Org + ";" + ID + ";" + Reason + ";"
                + date + ";" + Arrays.toString(TimeSlots) + ";" + Integer.toString(students) + ";" + room
                + ";False");
        output.close();
        System.out.println(
                "Applied for Booking Successfully. Please wait for Admin Approval.\nPlease keep a Note of Your Application Number "
                        + Integer.toString(number));
    }

    public Application(String Name, String Org, String ID, String Reason, Date date, String[] TimeSlots,
            int students, Room room) throws Exception {
        int number = 0;
        this.Name = Name;
        this.Org = Org;
        this.ID = ID;
        this.Reason = Reason;
        this.date = date;
        this.TimeSlots = TimeSlots;
        this.students = students;
        this.Status = false;
        this.room = room;
        Scanner input = new Scanner(new FileReader("AppNumber.txt"));
        while (input.hasNextLine()) {
            number = Integer.parseInt(input.nextLine().trim());
        }
        number++;
        PrintWriter output11 = new PrintWriter(new FileWriter("AppNumber.txt", false));
        output11.println(Integer.toString(number));
        output11.close();
        PrintWriter output12 = new PrintWriter(new FileWriter("Application.txt", true));
        output12.println(Integer.toString(number) + ";" + Name + ";" + Org + ";" + ID + ";" + Reason + ";"
                + date + ";" + Arrays.toString(TimeSlots) + ";" + Integer.toString(students) + ";" + room
                + ";False");
        output12.close();
        System.out.println(
                "Applied for Booking Successfully. Please wait for Admin Approval.\nPlease keep a Note of Your Application Number - "
                        + Integer.toString(number));
    }

    public Application() {
        int number = 0;
        this.Name = null;
        this.Org = null;
        this.ID = null;
        this.Reason = null;
        this.date = null;
        this.TimeSlots = null;
        this.students = 0;
        this.Status = false;
        this.room = null;
    }

    public boolean getStatus() {
        return this.Status;
    }

    public int getStudents() {
        return this.students;
    }

    public String getName() {
        return this.Name;
    }

    public String getOrg() {
        return this.Org;
    }

    public String getID() {
        return this.ID;
    }

    public String getReason() {
        return this.Reason;
    }

    public Date getDate() {
        return this.date;
    }

    public String[] getTimeSlots() {
        return this.TimeSlots;
    }

    public Room getRoom() {
        return this.room;
    }

    public boolean getMoreReq() {
        return this.moreReq;
    }

    public String getMoreInfo() {
        return this.moreInfo;
    }

    public int getAppNumber() {
        return this.applicationNumber;
    }

    public String getFinalTime() {
        return this.finalTime;
    }

    public void setMoreInfo(String info) throws Exception {
        this.moreInfo = info;
        PrintWriter output = new PrintWriter(new FileWriter(applio, true));
        output.println(Integer.toString(this.getAppNumber()) + ";" + this.getMoreInfo());
        output.close();
    }

    public void setTrue() {
        this.Status = true;
    }

    public void updateStatus(String time, Application appl) throws Exception {
        appl.setTrue();
        PrintWriter output = new PrintWriter(new FileWriter(applio, true));
        output.println(Integer.toString(appl.getAppNumber()) + ";Status;True");
        output.close();
        this.finalTime = time;
    }

    public void updateMoreInfo() throws Exception {
        this.moreReq = true;
        PrintWriter output = new PrintWriter(new FileWriter(applio, true));
        output.println(Integer.toString(this.getAppNumber()) + ";True");
        output.close();
    }

    public void BookingDetails() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Application Number : ");
        int AppNo = Integer.parseInt(in.nextLine().trim());
        String[] arr = {};
        String stat = "False";
        String finalTime = "";
        boolean disapprove = false;
        synchronized (this) {
            Scanner input1 = new Scanner(new FileReader("Application.txt"));
            while (input1.hasNextLine()) {
                String s = input1.nextLine().trim();
                String[] arr2 = s.split(";");
                if (arr2[0].equals(Integer.toString(AppNo))) {
                    stat = arr2[arr2.length - 1];
                    if (stat.equals("False") && arr2[arr2.length - 2].equals("Status")) {
                        disapprove = true;
                        arr = arr2;
                        break;
                    } else if (stat.equals("True") && arr2[arr2.length - 2].equals("Status")) {
                        finalTime = arr2[arr2.length - 3];
                        arr = arr2;
                    }
                    arr = arr2;
                    break;
                }
            }
        }
        String s1 = "";
        s1 += "Name : " + arr[1] + "\n";
        s1 += "ID : " + arr[3] + "\n";
        s1 += "Org : " + arr[2] + "\n";
        s1 += "Date : " + arr[5] + "\n";
        s1 += "Reason : " + arr[4] + "\n";

        if (finalTime.length() > 0) {
            s1 += "Time Slot : " + finalTime + "\n";
            s1 += "Status : Approved!";
        } else if (disapprove) {
            s1 += "Status : Approval Denied!";
        } else {
            s1 += "Status : Waiting for Approval!";
        }
        System.out.println(s1);
        if (arr[arr.length - 2].equals("AskMore")) {
            System.out.print("Admin Requests you to Elaborate the Reason of the Application : ");
            String Reason1 = in.nextLine().trim();
            synchronized (this) {
                PrintWriter output = new PrintWriter(new FileWriter(trio, false));
                Scanner input = new Scanner(new FileReader(applio));
                while (input.hasNextLine()) {
                    String s = input.nextLine().trim();
                    String[] arr3 = s.split(";");
                    if (Integer.parseInt(arr3[0]) != AppNo) {
                        output.println(s);
                    } else {
                        String s3 = "";
                        for (int i = 0; i < arr3.length - 2; i++) {
                            if (i == 4) {
                                s3 += Reason1;
                            } else {
                                s3 += arr3[i];
                            }
                            s3 += ";";
                        }
                        s3 += "False";
                        output.println(s3);
                    }
                }
                output.close();
                PrintWriter output1 = new PrintWriter(new FileWriter(applio, false));
                Scanner input11 = new Scanner(new FileReader(trio));
                while (input11.hasNextLine()) {
                    String s = input11.nextLine().trim();
                    output1.println(s);
                }
                output1.close();
            }
            System.out.println("Application Updated Successfully");
        }

    }

    public void cancelBooking() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Application Number : ");
        int num = Integer.parseInt(in.nextLine().trim());
        boolean contains = false;
        synchronized (this) {
            PrintWriter output = new PrintWriter(new FileWriter(trio, false));
            Scanner input = new Scanner(new FileReader(applio));
            while (input.hasNextLine()) {
                String s = input.nextLine().trim();
                String[] arr = s.split(";");
                if (Integer.parseInt(arr[0]) != num) {
                    output.println(s);
                } else {
                    contains = true;
                }
            }
            output.close();
            PrintWriter output1 = new PrintWriter(new FileWriter(applio, false));
            Scanner input1 = new Scanner(new FileReader(trio));
            while (input1.hasNextLine()) {
                String s = input1.nextLine().trim();
                output1.println(s);
            }
            output1.close();
        }
        if (contains) {
            System.out.println("Application Deleted Successfully");
        } else {
            System.out.println("Application Not Found");
        }
    }

    public static synchronized void AppliInfo() throws Exception {
        Scanner input1 = new Scanner(new FileReader("Application.txt"));
        boolean next = false;
        while (input1.hasNextLine()) {
            String s = input1.nextLine().trim();
            String[] arr = s.split(";");
            if (arr[arr.length - 1].equals("True")) {
                String pass;
            } else if (arr[arr.length - 1].equals("False") && arr[arr.length - 2].equals("Status")) {
                String pass;
            } else {
                System.out.println("----Application - " + arr[0] + "---------");
                String s1 = "";
                s1 += "Name : " + arr[1] + "\n";
                s1 += "ID : " + arr[3] + "\n";
                s1 += "Org : " + arr[2] + "\n";
                s1 += "Date : " + arr[5] + "\n";
                s1 += "Reason : " + arr[4] + "\n";
                s1 += "Time Slots : " + arr[6] + "\n";
                s1 += "Status : Waiting for Approval!";
                System.out.println(s1);
                System.out.println("--------------------------");
                Scanner in = new Scanner(System.in);
                System.out.println("Enter 1 to Approve the Application.");
                System.out.println("Enter 2 to Disapprove the Application.");
                System.out.println("Enter 3 to Ask More Information from Applicant.");
                System.out.println("Enter 4 for Next Application.");
                System.out.println("Enter 5 to Exit");
                String s0 = in.nextLine().trim();
                if (s0.equals("1")) {
                    System.out.print("Enter the Slot to Allot : ");
                    String finaltime = in.nextLine().trim();
                    Approve(Integer.parseInt(arr[0]), finaltime);
                } else if (s0.equals("2")) {
                    Disapprove(Integer.parseInt(arr[0]));
                } else if (s0.equals("3")) {
                    AskMoreInfo(Integer.parseInt(arr[0]));
                } else if (s0.equals("4")) {
                    next = true;
                    continue;
                } else {
                    System.out.println("Terminating Window...");
                    break;
                }
            }
            if (next) {
                System.out.println("No More Applications");
            }
        }
    }

    public static synchronized void Approve(int num, String finaltime) throws Exception {
        Scanner in = new Scanner(System.in);
        boolean contains = false;
        PrintWriter output = new PrintWriter(new FileWriter(trio, false));
        Scanner input = new Scanner(new FileReader(applio));
        while (input.hasNextLine()) {
            String s = input.nextLine().trim();
            String[] arr = s.split(";");
            if (Integer.parseInt(arr[0]) != num) {
                output.println(s);
            } else {
                contains = true;
                if (arr[arr.length - 3].equals("AskMore")) {
                    String s2 = "";
                    for (int i = 0; i < arr.length - 3; i++) {
                        s2 += arr[i];
                        s2 += ";";
                    }
                    s2 += Date.TimeProcess(finaltime) + ";";
                    s2 += "Status;True";
                    output.println(s2);
                } else {
                    String s2 = "";
                    for (int i = 0; i < arr.length - 1; i++) {
                        s2 += arr[i];
                        s2 += ";";
                    }
                    s2 += Date.TimeProcess(finaltime) + ";";
                    s2 += "Status;True";
                    output.println(s2);
                }
            }
        }
        output.close();
        PrintWriter output1 = new PrintWriter(new FileWriter(applio, false));
        Scanner input1 = new Scanner(new FileReader(trio));
        while (input1.hasNextLine()) {
            String s = input1.nextLine().trim();
            output1.println(s);
        }
        output1.close();
        if (contains) {
            System.out.println("Application Approved");
        } else {
            System.out.println("Application Not Found");
        }
    }

    public static synchronized void Disapprove(int num) throws Exception {
        boolean contains = false;
        PrintWriter output = new PrintWriter(new FileWriter(trio, false));
        Scanner input = new Scanner(new FileReader(applio));
        while (input.hasNextLine()) {
            String s = input.nextLine().trim();
            String[] arr = s.split(";");
            if (Integer.parseInt(arr[0]) != num) {
                output.println(s);
            } else {
                contains = true;
                if (arr[arr.length - 3].equals("AskMore")) {
                    String s2 = "";
                    for (int i = 0; i < arr.length - 3; i++) {
                        s2 += arr[i];
                        s2 += ";";
                    }
                    s2 += "Status;False";
                    output.println(s2);
                } else {
                    String s2 = "";
                    for (int i = 0; i < arr.length - 1; i++) {
                        s2 += arr[i];
                        s2 += ";";
                    }
                    s2 += "Status;False";
                    output.println(s2);
                }
            }
        }
        output.close();
        PrintWriter output1 = new PrintWriter(new FileWriter(applio, false));
        Scanner input1 = new Scanner(new FileReader(trio));
        while (input1.hasNextLine()) {
            String s = input1.nextLine().trim();
            output1.println(s);
        }
        output1.close();
        if (contains) {
            System.out.println("Application Disapproved");
        } else {
            System.out.println("Application Not Found");
        }
    }

    public static synchronized void AskMoreInfo(int num) throws Exception {
        boolean contains = false;
        PrintWriter output = new PrintWriter(new FileWriter(trio, false));
        Scanner input = new Scanner(new FileReader(applio));
        while (input.hasNextLine()) {
            String s = input.nextLine().trim();
            String[] arr = s.split(";");
            if (Integer.parseInt(arr[0]) != num) {
                output.println(s);
            } else {
                contains = true;
                if (arr[arr.length - 2].equals("Status")) {
                    System.out.println("Application Already Processed");
                    return;
                }
                String s2 = "";
                for (int i = 0; i < arr.length - 1; i++) {
                    s2 += arr[i];
                    s2 += ";";
                }
                s2 += "AskMore;False";
                output.println(s2);
            }
        }
        output.close();
        PrintWriter output1 = new PrintWriter(new FileWriter(applio, false));
        Scanner input1 = new Scanner(new FileReader(trio));
        while (input1.hasNextLine()) {
            String s = input1.nextLine().trim();
            output1.println(s);
        }
        output1.close();
        if (contains) {
            System.out.println("Application Sent Back");
        } else {
            System.out.println("Application Not Found");
        }
    }
}
