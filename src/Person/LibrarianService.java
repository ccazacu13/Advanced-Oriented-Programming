package Person;

import Utils.DbRead;
import Utils.DbWrite;
import Utils.Service;

import java.sql.SQLOutput;
import java.util.Scanner;

public class LibrarianService {

    public static String get_librarian_data(Librarian librarian, int id_library)
    {
        return "null," +
                "'" + librarian.getFirst_name() + "'" + "," +
                "'" + librarian.getLast_name() + "'" + "," +
                "'" + librarian.getCNP() + "'" + "," +
                "'" + librarian.getPhone() + "'" + "," +
                "'" + librarian.getBirth_date() + "'" + "," +
                "'" + librarian.getAddress().returnAddressData() + "'" + "," +
                + librarian.getSalary()  + "," +
                "'" + librarian.getContract_start() + "'" + ',' +
                id_library;
    }

    public static String get_librarian_update(Librarian librarian, int id_librarian)
    {
        return "SET " + "first_name = " + "'" + librarian.getFirst_name() + "'" + "," +
                "last_name = " + "'" + librarian.getLast_name() + "'" + "," +
                "cnp = " + "'" + librarian.getCNP() + "'" + "," +
                "phone_number = " + "'" + librarian.getPhone() + "'" + "," +
                "birth_date = " + "'" + librarian.getBirth_date() + "'" + "," +
                "address = " + "'" + librarian.getAddress().returnAddressData() + "'" + "," +
                "salary = " + librarian.getSalary() + "," +
                "contract_start = " + "'" + librarian.getContract_start() + "'"+
                " WHERE id_librarian = " + id_librarian;

    }
    public static void manage_librarians(Scanner scanner, int id_library)
    {
        while(true) {
            System.out.println();
            System.out.println("Choose an option: ");
            System.out.println("1.Add librarian.");
            System.out.println("2.Show librarians.");
            System.out.println("3.Update librarian.");
            System.out.println("4.Remove librarian.");
            System.out.println("5.Exit.");

            int choice;

            DbWrite dbWrite = DbWrite.getInstance();

            choice = Service.get_int(scanner, "Choice: ", "Invalid option, retry!");
            switch(choice)
            {
                case 1:
                    Librarian librarian = new Librarian();
                    librarian.read_person(scanner);
                    String data = LibrarianService.get_librarian_data(librarian, id_library);
                    dbWrite.add_to_database("librarians", data);
                    System.out.println("Librarian added successfully!");
                    System.out.println();
                    break;
                case 2:
                    DbRead dbRead = DbRead.getInstance();
                    dbRead.read_from_database("librarians", true, "WHERE id_library = " + id_library);
                    System.out.println();
                    break;
                case 3:
                    int id_librarian = Service.get_int(scanner, "Librarian to be updated: ", "Invalid value!");
                    Librarian new_librarian = new Librarian();
                    new_librarian.read_person(scanner);
                    String update_data = LibrarianService.get_librarian_update(new_librarian, id_librarian);
                    dbWrite.update_database("librarians", update_data);
                    System.out.println("Librarian updated successfully!");
                    System.out.println();
                    break;
                case 4:
                    int id_librarian_delete = Service.get_int(scanner, "Librarian to be deleted: ", "Invalid value!");
                    String remove_data1 = "WHERE id_librarian = " + id_librarian_delete;
                    dbWrite.remove_from_database("librarians", remove_data1);
                    System.out.println("Librarian deleted successfully!");
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
