package Book;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Fiction_book extends Book{

    private String genre;
    private boolean fictional_world;

    @Override
    protected void method(){}

    public Fiction_book() {
        super();
    }

    public Fiction_book(String title, int page_count, Date release_date, List<Integer> authors_ids, String genre, boolean fictional_world) {
        super(title, page_count, release_date, authors_ids);
        this.genre = genre;
        this.fictional_world = fictional_world;
    }

    @Override
    public void read_book(Scanner scanner){
        super.read_book(scanner);
        System.out.print("Genre: ");
        this.genre = scanner.nextLine();
        boolean check = true;
        while(check)
        {
                System.out.print("Is the action situated in a fictional world(yes/no): ");
                String response = scanner.nextLine();
                switch (response)
                {
                    case "yes": this.fictional_world = true;
                        check = false;
                        break;
                    case "no": this.fictional_world = false;
                        check = false;
                        break;
                    default:
                        System.out.println("This is not a valid response!");
                }

        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isFictional_world() {
        return fictional_world;
    }

    public void setFictional_world(boolean fictional_world) {
        this.fictional_world = fictional_world;
    }

    @Override
    public String toString() {
        return "Fiction_book{" +
                super.toString() + ", " +
                "genre='" + genre + '\'' +
                ", fictional_world=" + fictional_world +
                '}';
    }
}
