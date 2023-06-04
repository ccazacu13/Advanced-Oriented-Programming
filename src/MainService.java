import Library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Person.*;
import Library.*;

import java.security.Permission;
import java.util.*;
import Book.*;
import Utils.DbRead;
import Utils.DbWrite;
import Utils.Service;

public class MainService {

    private static MainService menu = null;
    private List<Library> libraries = new ArrayList<>();

    private MainService()
    {

    }

    public static MainService getInstance(){
        if(menu == null)
        {
            menu = new MainService();
        }

        return menu;
    }
    public void main() {
        DbWrite dbWrite = DbWrite.getInstance();
        int id;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the library management system!");
        String opt = "";
        DbRead dbRead = DbRead.getInstance();


        System.out.println();
        System.out.println("Choose the services: ");

        while(!opt.equals("6"))
        {
            int status = dbRead.read_from_database("libraries", false, "");
            System.out.println("1.Add Library.");
            System.out.println("2.List Libraries.");
            System.out.println("3.Manage Library.");
            System.out.println("4.Remove library.");
            System.out.println("5.Update library.");
            System.out.println("6.Done.");
            System.out.print("Option: ");
            opt = scanner.nextLine();

            switch (opt)
            {
                case "1": LibraryService.add_library(libraries, scanner);
                    break;
                case "2": LibraryService.list_libraries(libraries);
                    break;
                case "3":
                    if(status == 0) {
                        System.out.println("There are no available libraries");
                        System.out.println();
                        break;
                    }

                    dbRead.read_from_database("libraries", true, "");

                    boolean check = true;
                    int id_library = -1;
                    while(check)
                    {
                        id_library = Service.get_int(scanner, "Choose a library to manage: ", "This is an invalid value!");
                        if(id_library >= 1)
                        {
                            check = false;
                        }
                        else{
                            System.out.println("This is not a valid index");
                        }
                    }
                    LibraryService.manage_library(scanner, id_library);
                    break;
                case "4":
                    if(status == 0) {
                        System.out.println("There are no available libraries");
                        System.out.println();
                        break;
                    }
                    System.out.println("Choose a library to remove: ");
                    LibraryService.list_libraries(libraries);
                    check = true;
                    id = -1;
                    System.out.println("Choose the library you want to remove: ");
                    while(check)
                    {
                        try{
                            id = Integer.parseInt(scanner.nextLine());
                            check = false;
                        }catch (NumberFormatException e)
                        {
                            System.out.println("This is not a valid number!");
                            break;
                        }

                    }
//                    DbWrite dbWrite = DbWrite.getInstance();
                    dbWrite.remove_from_database("collect", "WHERE id_library = " + id);
                    dbWrite.remove_from_database("borrows", "WHERE id_library = " + id);
                    dbWrite.remove_from_database("librarians", "WHERE id_library = " + id);
                    dbWrite.remove_from_database("sign_up", "WHERE id_library = " + id);
                    LibraryService.remove_library(libraries, id);
                    break;

                case "5":
                    if(status == 0) {
                        System.out.println("There are no available libraries");
                        System.out.println();
                        break;
                    }
                    System.out.println("Choose a library to update: ");
                    LibraryService.list_libraries(libraries);
                    check = true;
                    id = -1;
                    System.out.println("Choose the library you want to update: ");
                    while(check)
                    {
                        try{
                            id = Integer.parseInt(scanner.nextLine());
                            check = false;
                        }catch (NumberFormatException e)
                        {
                            System.out.println("This is not a valid number!");
                            break;
                        }

                    }
                    Library lib = new Library();
                    lib.read_library(scanner);
                    String data = LibraryService.returnLibraryUpdate(lib) + " WHERE id_library = " + id;


                    dbWrite.update_database("libraries", data);
                    System.out.println();

                    System.out.println("Library updated successfully! ");
                    System.out.println();
                    break;
                case "6":
                    System.out.println();
                    System.out.println("--------------- Application exit successfully! -------------");
                    break;
                default:
                    System.out.println("Invalid option!");;
            }
        }
        scanner.close();

    }
}
