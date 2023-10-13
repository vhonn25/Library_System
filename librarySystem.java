import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class librarySystem {
    private Stack<String> availableBooks;
    private Stack<String> borrowedBooks;
    private Stack<String> catalog;

    public librarySystem() {
        availableBooks = new Stack<>();
        borrowedBooks = new Stack<>();
        catalog = new Stack<>();
        initializeAvailableBooks();
    }

    private void initializeAvailableBooks() {
        availableBooks.push("Harry Potter | J.K. Rowling");
        availableBooks.push("Florante at Laura | Francisco Balagtas");
        availableBooks.push("Nineteen Eighty-Four | George Orwell");
        availableBooks.push("The Lord of the Rings | J.R.R. Tolkien");
        availableBooks.push("The Book Thief | Markus Zusak");
    }

    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        int bookNumber = 1;
        for (String book : availableBooks) {
            System.out.println("[" + bookNumber + "] " + book);
            bookNumber++;
        }
    }

    public void addBook(String title, String author) {
            String book = title + " | " + author;
            availableBooks.push(book);
            catalog.push(book);
            System.out.println("\nAdded book: " + book);
        }

    public void borrowBook(int bookNumber) {
        if (bookNumber >= 1 && bookNumber <= availableBooks.size()) {
            String book = availableBooks.remove(bookNumber - 1);
            borrowedBooks.push(book);
            System.out.println("\nBorrowed: " + book);
        } else {
            System.out.println("\nInvalid book number.");
        }
    }

    public void returnBook(String title) {
        String matchingTitle = findMatchingTitleInBorrowedBooks(title);
        if (matchingTitle != null) {
            borrowedBooks.remove(matchingTitle);
            availableBooks.push(matchingTitle);
            System.out.println("\nReturned book: " + matchingTitle);
        } else {
            System.out.println("\nYou don't have this book borrowed.");
        }
    }

    private String findMatchingTitleInBorrowedBooks(String title) {
        for (String book : borrowedBooks) {
            if (book.startsWith(title + " | ")) {
                return book;
            }
        }
        return null;
    }

    public void displayBorrowedBooks() {
        System.out.println("\nBorrowed Books:");
        for (String book : borrowedBooks) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        librarySystem library = new librarySystem();

        while (true) {
            System.out.println("\n----------------------------------");
            System.out.println("|       BOOK LIBRARY SYSTEM      |");
            System.out.println("----------------------------------");
            System.out.println("1. Add a book to the library     |");
            System.out.println("2. Display available books       |");
            System.out.println("3. Borrow a book                 |");
            System.out.println("4. Return a book                 |");
            System.out.println("5. Display borrowed books        |");
            System.out.println("6. Exit                          |");
            System.out.println("----------------------------------");

            System.out.print("\nEnter your choice: ");
            String choiceInput = scanner.nextLine();

            if (!choiceInput.matches("[1-6]")) {
                System.out.println("\nError: Invalid choice. Please choose a valid option.");
                continue;
            }

            int choice = Integer.parseInt(choiceInput);

            switch (choice) {
                case 1:
                    System.out.print("Enter the book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    library.displayAvailableBooks();
                    break;
                case 3:
                	library.displayAvailableBooks();
                    System.out.print("\nEnter the book number you want to borrow: ");
                    int bookNumber = Integer.parseInt(scanner.nextLine());
                    library.borrowBook(bookNumber);
                    break;
                case 4:
                	library.displayBorrowedBooks();
                    System.out.print("\nEnter the book title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 5:
                    library.displayBorrowedBooks();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
    }
}
