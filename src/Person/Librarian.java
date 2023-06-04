package Person;

import Library.Library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Librarian extends Person{

    private static int id = 0;

    private final int librarian_id;
    private double salary;

    private Date contract_start;

    protected void method(){}

    public Librarian(){
        this.librarian_id = ++id;
    }

    public Librarian(String first_name, String last_name, String CNP, String phone, Date birth_date, Address address, double salary, Date contract_start) {
        super(first_name, last_name, CNP, phone, birth_date, address);
        this.librarian_id = id++;
        this.salary = salary;
        this.contract_start = contract_start;
    }

    @Override
    public void read_person(Scanner scanner){
        super.read_person(scanner);
        boolean check = true;
        while(check)
        {
            try{
                System.out.print("Salary: ");
                this.salary = Integer.parseInt(scanner.nextLine());
                check = false;
            }catch(NumberFormatException e)
            {
                System.out.println("This is not a valid wage!");
            }
        }
        check = true;
        while(check)
        {
            try{
                System.out.print("Contract starting date: ");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = scanner.nextLine();
                this.contract_start = dateFormat.parse(date);
                check = false;
            }catch(ParseException e)
            {
                System.out.println("This is not a starting date!");
            }
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getContract_start() {
        return contract_start;
    }

    public void setContract_start(Date contract_start) {
        this.contract_start = contract_start;
    }

    public int getLibrarian_id() {
        return librarian_id;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                super.toString() + ", " +
                "salary=" + salary +
                ", contract_start=" + contract_start +
                '}';
    }
}
