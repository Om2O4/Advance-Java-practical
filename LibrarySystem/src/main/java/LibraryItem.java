public abstract class LibraryItem {
    protected String title;

    public LibraryItem(String title) {
        this.title = title;
    }

    public abstract int getBorrowDuration();

    public void borrow() {
        System.out.println("Borrowing: " + title);
    }
}