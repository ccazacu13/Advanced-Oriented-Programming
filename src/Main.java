import Person.*;
import Library.*;

import java.security.Permission;
import java.util.*;
public class Main {
    public static List<Library> libraries = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the library management system!");
        String opt = "";

        System.out.println("Choose the services: ");

        while(!opt.equals("5"))
        {
            System.out.println("1.Add Library.");
            System.out.println("2.List Libraries.");
            System.out.println("3.Manage Library.");
            System.out.println("4.Remove library.");
            System.out.println("5.Done.");
            System.out.print("Option: ");
            opt = scanner.nextLine();

            switch (opt)
            {
                case "1": LibraryService.add_library(libraries, scanner);
                    break;
                case "2": LibraryService.list_libraries(libraries);
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println("Choose a library to remove: ");
                    LibraryService.list_libraries(libraries);

                    boolean check = true;
                    int id = -1;
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
                    LibraryService.remove_library(libraries, id);
                    break;
                case "5":
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