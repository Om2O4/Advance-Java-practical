public class DVD extends LibraryItem {
    private int duration;

    public DVD(String title, int duration) {
        super(title);
        this.duration = duration;
    }

    public int getBorrowDuration() {
        return 7;
    }
}