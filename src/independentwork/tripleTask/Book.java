package lesson1.tripleTask;

public class Book {
    private String title;
    private Page[] pages;
    public Book(String title, Page[] pages) {
        this.title = title;
        this.pages = pages;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Page[] getPages() {
        return pages;
    }
    public void setPages(Page[] pages) {
        this.pages = pages;
    }
    public void openBook() {
        System.out.println("Opening Book" + title);
        for (Page page : pages) {
            page.read();
        }
    }
}
