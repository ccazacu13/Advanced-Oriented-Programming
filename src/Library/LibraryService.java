package Library;

import Book.*;
import Person.Librarian;
import Person.Reader;
import jdk.swing.interop.LightweightContentWrapper;

import java.util.*;

public class LibraryService {

    public static void add_library(List<Library> libraries, Scanner scanner)
    {
        Library library = new Library();
        library.read_library(scanner);
        libraries.add(library);
        System.out.println("Library added successfully!");
        System.out.println();
    }

    public static void list_libraries(List<Library> libraries) {
        System.out.println();
        if (libraries.size() == 0)
            System.out.println("There are no libraries");

        else {
            int index = 1;
            for (Library library : libraries) {
                System.out.println(index++ + ". " + library.getName());
            }
            System.out.println("There are " + libraries.size() + " libraries");
        }
        System.out.println();
    }

    public static void remove_library(List<Library> libraries, int id)
    {
        if (libraries.size() == 0) {
            return;
        }

        if( id >= 0 && id < libraries.size()) {
            libraries.remove(id - 1);
            System.out.println("Library removed successfully!");
        }
        else{
            System.out.println("This is not a valid Library number!");
        }
        System.out.println();
    }

    public static void manage_library(Scanner scanner, List<Library> libraries, int id)
    {
        System.out.println("Now we are managing library:  " + libraries.get(id).getName());
        String opt = "";
        while(!opt.equals("6")) {
            System.out.println("Options for managing the library: ");
            System.out.println("1.Add a reader.");
            System.out.println("2.Hire a librarian.");
            System.out.println("3.Add a book to the collection.");
            System.out.println("4.Borrow a book.");
            System.out.println("5.List the books by popularity.");
            System.out.println("6.Back");
            System.out.print("Option: ");
            opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    Reader reader = new Reader();
                    reader.read_person(scanner);
                    libraries.get(id).getReaders().put(reader.getReader_id(), reader);
                    System.out.println("Reader added successfully!");
                    System.out.println();
                    break;
                case "2":
                    Librarian librarian = new Librarian();
                    librarian.read_person(scanner);
                    libraries.get(id).getEmployees().put(librarian.getLibrarian_id(), librarian);
                    System.out.println("Librarian added successfully!");
                    System.out.println();
                    break;
                case "3":
                    System.out.println("What kind of book do you want to add(fiction/non-fiction): ");
                    boolean check = true;
                    while(check) {
                        System.out.print("Option: ");
                        String type = scanner.nextLine();
                        switch (type)
                        {
                            case "fiction":
                                Fiction_book book = new Fiction_book();
                                book.read_book(scanner);
                                libraries.get(id).getCollection().add(book);
                                check = false;
                                System.out.println("Fiction book was added successfully!");
                                System.out.println();
                                break;
                            case "non-fiction":
                                Non_fiction_book non_fiction_book = new Non_fiction_book();
                                non_fiction_book.read_book(scanner);
                                libraries.get(id).getCollection().add(non_fiction_book);
                                check = false;
                                System.out.println("Non-fiction book was added successfully!");
                                System.out.println();
                                break;
                            default:
                                System.out.println("This is not a valid option!");
                        }
                    }
                    break;
                case "4":
                    Library library = libraries.get(id);
                    if(library.getCollection().size() == 0 || library.getEmployees().size() == 0 || library.getReaders().size() == 0) {
                        System.out.println("There are not enough resources!");
                        return;
                    }
                    Borrow borrow = new Borrow();
                    borrow.read_borrow(scanner);
                    libraries.get(id).getBorrow_history().add(borrow);
                    int book_id = borrow.getBook_id();
                    for(Book book: library.getCollection())
                        if(book.getBook_id() == book_id) {
                            book.increase_popularity();
                            break;
                        }
                    System.out.println("The borrow history was updated successfully!");
                    System.out.println();
                    break;
                case "5":
                    System.out.println();
                    Library lib = libraries.get(id);
                    if(lib.getCollection().size() == 0)
                    {
                        System.out.println("There a no books available!");
                        return;
                    }
                    System.out.println();
                    System.out.println("The books sorted by popularity: ");
                    BookPopularityComparator comparator = new BookPopularityComparator();
                    lib.getCollection().sort(comparator);
                    for(int i = 1; i <= lib.getCollection().size(); i++) {
                        System.out.println(i + ". " + lib.getCollection().get(i-1).getTitle() + ", borrowed: " + lib.getCollection().get(i-1).getPopularity() + " times.");
                    }
                    System.out.println();
                    break;
                case "6":
                    System.out.println("We have returned to the first options.");
                    System.out.println();
                    break;
                default:
                    System.out.println("This is not a valid option");
            }
        }
    }
}
