public class LargerRoomException extends Exception {

    public LargerRoomException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage() + "\nPlease Select a Larger Room!";
    }
}
