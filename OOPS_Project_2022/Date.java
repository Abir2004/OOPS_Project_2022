public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static String TimeProcess(String s) {
        String s1 = "";
        s1 += s.substring(0, 2);
        s1 += ":";
        s1 += s.substring(2, 4) + " to ";
        s1 += s.substring(5, 7);
        s1 += ":";
        s1 += s.substring(7, 9);
        return s1;
    }

    public String toString() {
        return Integer.toString(this.day) + "/" + Integer.toString(this.month) + "/" + Integer.toString(this.year);
    }
}
