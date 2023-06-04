package Library;

import Utils.DbRead;
import Utils.DbWrite;
import Utils.Service;

import java.sql.SQLOutput;
import java.util.Scanner;

public class BorrowService {

    public static String get_borrow_data(Borrow borrow)
    {
        return "null," +
                + borrow.getLibrary_id() + "," +
                + borrow.getBook_id() + "," +
                + borrow.getLibrarian_id() + "," +
                + borrow.getReader_id();
    }

    public static String get_borrow_update(Borrow borrow, int id_barrow)
    {
        return "SET " + "id_library = " + borrow.getLibrary_id() + "," +
                "id_book = " + borrow.getBook_id() + "," +
                "id_librarian = " + borrow.getLibrarian_id() + "," +
                "id_reader = " + borrow.getReader_id() +
                " WHERE id_borrow = " + id_barrow;

    }
    public static void manage_borrows(Scanner scanner, int id_library)
    {
        while(true) {
            System.out.println();
            System.out.println("Choose an option: ");
            System.out.println("1.Borrow a book.");
            System.out.println("2.Show borrow history.");
            System.out.println("3.Update borrow.");
            System.out.println("4.Remove borrow from history.");
            System.out.println("5.Exit.");

            int choice;

            DbWrite dbWrite = DbWrite.getInstance();

            choice = Service.get_int(scanner, "Choice: ", "Invalid option, retry!");
            switch(choice)
            {
                case 1:
                    Borrow borrow = new Borrow();
                    borrow.read_borrow(scanner);
                    borrow.setLibrary_id(id_library);
                    String data = BorrowService.get_borrow_data(borrow);
                    dbWrite.add_to_database("borrows", data);
                    System.out.println("Book was borrowed successfully!");
                    System.out.println();
                    break;
                case 2:
                    DbRead dbRead = DbRead.getInstance();
                    dbRead.read_from_database("borrows", true, "WHERE id_library = " + id_library);
                    System.out.println();
                    break;
                case 3:
                    int id_borrow = Service.get_int(scanner, "Borrow to be updated: ", "Invalid value!");
                    Borrow new_borrow = new Borrow();
                    new_borrow.read_borrow(scanner);
                    new_borrow.setLibrary_id(id_library);
                    String update_data = BorrowService.get_borrow_update(new_borrow, id_borrow);
                    dbWrite.update_database("borrows", update_data);
                    System.out.println("Borrow updated successfully!");
                    System.out.println();
                    break;
                case 4:
                    int id_borrow_delete = Service.get_int(scanner, "Borrow to be deleted: ", "Invalid value!");
                    String remove_data1 = "WHERE id_borrow = " + id_borrow_delete;
                    dbWrite.remove_from_database("borrows", remove_data1);
                    System.out.println("Borrow deleted successfully!");
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    return;
                default:
                    System.out.println("This is not a valid option!");
            }
        }

    }
}
