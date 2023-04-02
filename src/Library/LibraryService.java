package Library;

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

    public static void manage_library(List<Library> libraries, int id)
    {
        System.out.println("Now we are managing library:  " + libraries.get(id).getName());
        String opt = "";

    }
}
