import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book class (Encapsulation)
class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    private String issuedTo;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.issuedTo = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void issueBook(String userName) {
        if (isAvailable) {
            isAvailable = false;
            issuedTo = userName;
            System.out.println(userName + " successfully issued the book: " + title);
        } else {
            System.out.println("Book is already issued to " + issuedTo);
        }
    }

    public void returnBook() {
        isAvailable = true;
        issuedTo = null;
    }
}

// Library class (Manages books and users)
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        User newUser = new User(name);
        users.add(newUser);
        return newUser;
    }

    public void displayBooks() {
        System.out.println("Available Books in the Library:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + (book.isAvailable() ? " (Available)" : " (Issued to: " + book.getIssuedTo() + ")"));
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

// User class (Handles book issuance and return)
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void issueBook(Library library, String bookTitle) {
        Book book = library.findBook(bookTitle);
        if (book != null) {
            book.issueBook(name);
        } else {
            System.out.println("Book not found in the library.");
        }
    }

    public void returnBook(Library library, String bookTitle) {
        Book book = library.findBook(bookTitle);
        if (book != null && name.equals(book.getIssuedTo())) {
            book.returnBook();
            System.out.println(name + " returned the book: " + bookTitle);
        } else {
            System.out.println("You cannot return a book that you didn't issue.");
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        // Adding books to the library
        library.addBook(new Book("The Alchemist", "Paulo Coelho"));
        library.addBook(new Book("Harry Potter", "J.K. Rowling"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("Moby Dick", "Herman Melville"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger"));
        
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Display Books");
            System.out.println("2. Search & Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    User user = library.findUser(userName);
                    System.out.print("Enter book title to issue: ");
                    String bookTitle = scanner.nextLine();
                    user.issueBook(library, bookTitle);
                    break;
                case 3:
                    System.out.print("Enter your name: ");
                    String returnUser = scanner.nextLine();
                    User returningUser = library.findUser(returnUser);
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    returningUser.returnBook(library, returnTitle);
                    break;
                case 4:
                    System.out.println("Exiting the Library System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}