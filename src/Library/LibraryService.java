package Library;
import Person.LibrarianService;
import Person.ReaderService;
import Utils.DbRead;
import Utils.DbWrite;

import Book.*;
import Person.Librarian;
import Person.Reader;
import jdk.swing.interop.LightweightContentWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class LibraryService {

    public static String returnLibraryData(Library library){
        String to_return = "null,"
                + "'" + library.getName() + "'" + ','
                + "'" + library.getPhone() + "'" + ','
                + "'" + library.getEmail() + "'" + ','
                + "'" + library.getAddress().returnAddressData() + "'" + ','
                + library.getBook_count();
        return to_return.strip();
    }

    public static String returnLibraryUpdate(Library library)
    {
        return
                "SET name = " + "'" + library.getName() + "'" + ", " +
                "phone = " + "'" + library.getPhone() + "'" + ", " +
                "email = " + "'" + library.getEmail() + "'" + ", " +
                "address = " + "'" + library.getAddress().returnAddressData() + "'" + ", " +
                "book_count = " + "'" + library.getBook_count() + "'";
    }
    public static void add_library(List<Library> libraries, Scanner scanner)
    {
        Library library = new Library();
        library.read_library(scanner);

        String data = LibraryService.returnLibraryData(library);
        DbWrite dbWrite = DbWrite.getInstance();
        dbWrite.add_to_database("libraries", data.strip());

        System.out.println("Library added successfully!");
        System.out.println();
    }

    public static void list_libraries(List<Library> libraries) {
        DbRead dbRead = DbRead.getInstance();
        int status = dbRead.read_from_database("libraries", true, "");
        System.out.println("\n----------------------------");

        if (status == 0)
            System.out.println("There are no libraries");

        System.out.println();
    }

    public static void remove_library(List<Library> libraries, int id)
    {
        DbWrite dbWrite = DbWrite.getInstance();
        DbRead dbRead = DbRead.getInstance();

        int status = dbRead.read_from_database("libraries", false, "");
        if(status == 0)
            return;


        dbWrite.remove_from_database("libraries", "WHERE id_library = " + id);
        System.out.println("Library removed successfully!");


    }

    public static void manage_library(Scanner scanner, int id_library)
    {
        System.out.println("Managing library: ");
        System.out.println();
        String opt = "";
        while(!opt.equals("6")) {
            System.out.println("Options for managing the library: ");
            System.out.println("1.Manage readers");
            System.out.println("2.Manage librarians");
            System.out.println("3.Manage books");
            System.out.println("4.Manage borrows.");
            System.out.println("5.List the books by popularity.");
            System.out.println("6.Back");
            System.out.print("Option: ");
            opt = scanner.nextLine();

            switch (opt) {
                case "1":
                    ReaderService.manage_readers(scanner, id_library);
                    break;
                case "2":
                    LibrarianService.manage_librarians(scanner, id_library);
                    break;
                case "3":
                    BookService.manage_books(scanner, id_library);
                    break;
                case "4":
                    BorrowService.manage_borrows(scanner, id_library);
                    break;
                case "5":

                    break;
                case "6":
                    return;
                default:
                    System.out.println("This is not a valid option");
            }
        }


    }
}
