public class Book extends LibraryItem {
    private int pages;

    public Book(String title, int pages) {
        super(title);
        this.pages = pages;
    }
    public int getBorrowDuration() {
        return 14; 
    }
}