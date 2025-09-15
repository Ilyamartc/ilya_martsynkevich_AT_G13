package lesson1.tripleTask;

public class Runner {
    public static void main(String[] args) {
        Page page1 = new Page(1, "Fuck");
        Page page2 = new Page(2, "them");
        Page page3 = new Page(3, "all");
        Page[] pages = {page1, page2, page3};
        Book book1 = new Book(" My life", pages);
        book1.openBook();
    }
}
