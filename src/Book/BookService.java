package Book;

import Utils.DbRead;
import Utils.DbWrite;
import Utils.Service;

import java.sql.SQLOutput;
import java.util.Scanner;

public class BookService {

    public static String get_book_data(Book book)
    {
        if(book instanceof Fiction_book)
            return "null" + "," +
                    "'" + book.getTitle() + "'" + "," +
                    book.getPage_count()  + "," +
                    "'" + book.getRelease_date() + "'" + "," +
                    book.getPopularity()  + "," +
                    "'" + ((Fiction_book) book).getGenre() + "'" + "," +
                    "'" + ((Fiction_book) book).isFictional_world() + "'" + "," +
                    "null" + "," +
                    "null";
        else
            if(book instanceof  Non_fiction_book)
                return  "null" + "," +
                        "'"  + book.getTitle() + "'" +  "," +
                    + book.getPage_count()  + "," +
                    "'" + book.getRelease_date() + "'" + "," +
                    + book.getPopularity()  + "," +
                    "null" + "," +
                    "null" + "," +
                    "'" + ((Non_fiction_book) book).getSubject() + "'" + "," +
                    "'" + ((Non_fiction_book) book).getReferences() + "'";
        return "";
    }

    public static String get_book_update(Book book, int id_book)
    {
        if(book instanceof  Fiction_book)
            return
                "SET " + "TITLE = " + "'" + book.getTitle() + "'" + "," +
                        "PAGE_COUNT = " + "'" + book.getPage_count() + "'" + "," +
                        "release_date = " + "'" + book.getRelease_date() + "'" + "," +
                        "popularity = " + "'" + book.getPopularity() + "'" + "," +
                        "genre = " + "'" + ((Fiction_book) book).getGenre() + "'" + "," +
                        "fictional_world = " + "'" + ((Fiction_book) book).isFictional_world() + "'" + " " +
                        "WHERE id_book = " + id_book;
        else
            return
                    "SET " + "TITLE = " + "'" + book.getTitle() + "'" + "," +
                            "PAGE_COUNT = " + "'" + book.getPage_count() + "'" + "," +
                            "release_date = " + "'" + book.getRelease_date() + "'" + "," +
                            "popularity = " + "'" + book.getPopularity() + "'" + "," +
                            "subject = " + "'" + ((Non_fiction_book) book).getSubject() + "'" + "," +
                            "references = " + "'" + ((Non_fiction_book) book).getReferences() + "'" + " " +
                            "WHERE id_book = " + id_book;

    }
    public static void manage_books(Scanner scanner, int id_library)
    {
        while(true) {
            System.out.println();
            System.out.println("Choose an option: ");
            System.out.println("1.Add book.");
            System.out.println("2.Show books.");
            System.out.println("3.Update book.");
            System.out.println("4.Remove book.");
            System.out.println("5.Exit.");

            int choice;

            DbWrite dbWrite = DbWrite.getInstance();

            choice = Service.get_int(scanner, "Choice: ", "Invalid option, retry!");
            switch(choice)
            {
                case 1:
                    System.out.println("What kind of book: ");
                    System.out.println("1.Fictional book.");
                    System.out.println("2.Non fictional book.");
                    String book_option = scanner.nextLine().strip();
                    Book book = null;
                    if(book_option.equals("1"))
                        book = new Fiction_book();
                    else
                        book = new Non_fiction_book();

                    book.read_book(scanner);
                    String data = BookService.get_book_data(book);
                    dbWrite.add_to_database("books", data);
                    dbWrite.add_to_database("collect", "null," + book.getBook_id() + "," + id_library);
                    System.out.println("Book added successfully!");
                    System.out.println();
                    break;
                case 2:
                    DbRead dbRead = DbRead.getInstance();
                    dbRead.read_from_database("books", true,
                            ",COLLECT WHERE COLLECT.ID_BOOK = BOOKS.ID_BOOK");
                    System.out.println();
                    break;
                case 3:
                    int id_book = Service.get_int(scanner, "book to be updated: ", "Invalid value!");
                    System.out.println("What kind of book: ");
                    System.out.println("1.Fictional book.");
                    System.out.println("2.Non fictional book.");
                    System.out.println("Answer: ");
                    String answer = scanner.nextLine();
                    Book new_book;
                    if(answer.equals("1"))
                        new_book = new Fiction_book();
                    else
                        new_book = new Non_fiction_book();
                    new_book.read_book(scanner);

                    String update_data = BookService.get_book_update(new_book, id_book);
                    dbWrite.update_database("books", update_data);
                    System.out.println("Book updated successfully!");
                    System.out.println();
                    break;
                case 4:
                    int id_book_delete = Service.get_int(scanner, "Book to be deleted: ", "Invalid value!");
                    String remove_data1 = "WHERE id_book = " + id_book_delete;
                    String remove_data2 = "WHERE id_book = " + id_book_delete + " AND " + "id_library = " + id_library;
                    dbWrite.remove_from_database("collect", remove_data2);
                    dbWrite.remove_from_database("books", remove_data1);
                    System.out.println("Book deleted successfully!");
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
