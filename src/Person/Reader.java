package Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Reader extends Person{

    private static int id = 0;

    private final int reader_id;
    private Date sign_up_date;

    private String favorite_genre;
    @Override
    protected void method(){}

    public Reader(){
        this.reader_id = ++id;
    }
    public Reader(String first_name, String last_name, String CNP, String phone, Date birth_date, Address address, Date sign_up_date, String favorite_genre) {
        super(first_name, last_name, CNP, phone, birth_date, address);
        this.reader_id = ++id;
        this.sign_up_date = sign_up_date;
        this.favorite_genre = favorite_genre;
    }

    public Date getSign_up_date() {
        return sign_up_date;
    }

    public void setSign_up_date(Date sign_up_date) {
        this.sign_up_date = sign_up_date;
    }

    public String getFavorite_genre() {
        return favorite_genre;
    }

    public void setFavorite_genre(String favorite_genre) {
        this.favorite_genre = favorite_genre;
    }

    public int getReader_id() {
        return reader_id;
    }

    @Override
    public void read_person(Scanner scanner){
        super.read_person(scanner);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean check = true;
        while(check)
        {
            try{
                System.out.print("Sign up date: ");
                String date = scanner.nextLine();
                this.sign_up_date = dateFormat.parse(date);
                check = false;
            }catch (ParseException e)
            {
                System.out.println("This is not a valid date!");
            }
        }
        System.out.print("Favorite genre: ");
        this.favorite_genre = scanner.nextLine();

    }
    @Override
    public String toString() {
        return "Reader{" +
                super.toString() + ", " +
                "sign_up_date=" + sign_up_date +
                ", favorite_genre=" + favorite_genre +
                '}';
    }
}
