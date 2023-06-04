package Person;

import Utils.DbRead;
import Utils.DbWrite;
import Utils.Service;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ReaderService {

    public static String get_reader_data(Reader reader, int id_library)
    {
        return "null," +
                "'" + reader.getFirst_name() + "'" + "," +
                "'" + reader.getLast_name() + "'" + "," +
                "'" + reader.getCNP() + "'" + "," +
                "'" + reader.getPhone() + "'" + "," +
                "'" + reader.getBirth_date() + "'" + "," +
                "'" + reader.getAddress().returnAddressData() + "'" + "," +
                "'" + reader.getSign_up_date() + "'" + "," +
                "'" + reader.getFavorite_genre() + "'" + "," +
                id_library;
    }

    public static String get_reader_update(Reader reader, int id_reader)
    {
        return "SET " + "first_name = " + "'" + reader.getFirst_name() + "'" + "," +
                "last_name = " + "'" + reader.getLast_name() + "'" + "," +
                "cnp = " + "'" + reader.getCNP() + "'" + "," +
                "phone_number = " + "'" + reader.getPhone() + "'" + "," +
                "birth_date = " + "'" + reader.getBirth_date() + "'" + "," +
                "address = " + "'" + reader.getAddress().returnAddressData() + "'" + "," +
                "sign_up_date = " + "'" + reader.getSign_up_date() + "'" + "," +
                "favorite_genre = " + "'" + reader.getFavorite_genre() + "'" +
                " WHERE id_reader = " + id_reader ;

    }
    public static void manage_readers(Scanner scanner, int id_library)
    {
        while(true) {
            System.out.println();
            System.out.println("Choose an option: ");
            System.out.println("1.Add reader.");
            System.out.println("2.Show readers.");
            System.out.println("3.Update reader.");
            System.out.println("4.Remove reader.");
            System.out.println("5.Exit.");

            int choice;

            DbWrite dbWrite = DbWrite.getInstance();

            choice = Service.get_int(scanner, "Choice: ", "Invalid option, retry!");
            switch(choice)
            {
                case 1:
                    Reader reader = new Reader();
                    reader.read_person(scanner);
                    String data = ReaderService.get_reader_data(reader, id_library);
                    dbWrite.add_to_database("readers", data);
                    System.out.println("Reader added successfully!");
                    System.out.println();
                    break;
                case 2:
                    DbRead dbRead = DbRead.getInstance();
                    dbRead.read_from_database("readers", true, "WHERE id_library = " + id_library);
                    System.out.println();
                    break;
                case 3:
                    int id_reader = Service.get_int(scanner, "Reader to be updated: ", "Invalid value!");
                    Reader new_reader = new Reader();
                    new_reader.read_person(scanner);
                    String update_data = ReaderService.get_reader_update(new_reader, id_reader);
                    dbWrite.update_database("readers", update_data);
                    System.out.println("Reader updated successfully!");
                    System.out.println();
                    break;
                case 4:
                    int id_reader_delete = Service.get_int(scanner, "Reader to be deleted: ", "Invalid value!");
                    String remove_data1 = "WHERE id_reader = " + id_reader_delete;
                    dbWrite.remove_from_database("readers", remove_data1);
                    System.out.println("Reader deleted successfully!");
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
