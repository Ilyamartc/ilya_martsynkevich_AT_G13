package lesson1.library;

public class Runner {
    public static void main(String[] args) {
        Book book1 = new Book("Анна Каренина", "Лев Толстой", 1878);
        Book book2 = new Book("Война и мир", "Лев Толстой", 1868);
        Book book3 = new Book("Чем люди живы", "Лев Толстой", 1881);
        Book[] books =  {book1, book2, book3};
        Library library = new Library("Городская библиотека");
        library.addBooks(books);

        library.printAllBooks();
    }
}
