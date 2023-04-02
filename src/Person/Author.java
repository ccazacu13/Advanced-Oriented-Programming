package Person;

import java.util.Date;
import java.util.Scanner;

public class Author extends Person{

    private static int id = 0;

    private final int author_id;
    private String favorite_genre;

    private int book_count;
    @Override
    protected void method(){}

    public Author()
    {
        this.author_id = id++;
    }

    public Author(String first_name, String last_name, String CNP, String phone, Date birth_date, Address address, String favorite_genre, int book_count) {
        super(first_name, last_name, CNP, phone, birth_date, address);
        this.author_id = id++;
        this.favorite_genre = favorite_genre;
        this.book_count = book_count;
    }

    public String getFavorite_genre() {
        return favorite_genre;
    }

    public void setFavorite_genre(String favorite_genre) {
        this.favorite_genre = favorite_genre;
    }

    public int getBook_count() {
        return book_count;
    }

    public void setBook_count(int book_count) {
        this.book_count = book_count;
    }

    public int getAuthor_id() {
        return author_id;
    }

    @Override
    public void read_person(Scanner scanner)
    {
        super.read_person(scanner);
        System.out.print("Favorite genre: ");
        this.favorite_genre = scanner.nextLine();
        boolean check = true;
        while(check)
        {
            try{
                System.out.print("Book count: ");
                this.book_count = Integer.parseInt(scanner.nextLine());
                check = false;
            }catch(NumberFormatException e)
            {
                System.out.println("This is not a valid book count!");
            }
        }
    }
    @Override
    public String toString() {
        return "Author{" +
                super.toString() + ", " +
                "favorite_genre='" + favorite_genre + '\'' +
                ", book_count=" + book_count +
                '}';
    }
}
