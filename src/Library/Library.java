package Library;

import Person.*;
import Book.*;
import java.util.*;

public class Library {
    private static int id;

    private final int library_id;

    private String name, phone, email;

    private Address address;

    private int book_count;

    private Map<Integer, Person> readers = new HashMap<>();

    private Map<Integer, Librarian> employees = new HashMap<>();
    private List<Book> collection = new ArrayList<Book>();

    private List<Borrow> borrow_history = new ArrayList<>();

    public Library(){
        this.library_id = id++;
    }

    public Library(String name, String phone, String email, Address address, int book_count) {
        this.library_id = id++;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.book_count = book_count;
    }

    public void read_library(Scanner scanner){
//        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Name: ");
        this.name = scanner.nextLine();
        System.out.print("Phone: ");
        this.phone = scanner.nextLine();
        System.out.print("Email: ");
        this.email = scanner.nextLine();

        boolean check = true;
        while(check) {
            try {
                System.out.print("Book count: ");
                this.book_count = Integer.parseInt(scanner.nextLine());
                check = false;
            }
            catch (NumberFormatException e)
            {
                System.out.println("This is not a valid number!");
            }
        }
        System.out.println("Address: ");
        this.address = new Address();
        this.address.read_address(scanner);

//        scanner.close();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public int getBook_count() {
        return book_count;
    }

    public void setBook_count(int book_count) {
        this.book_count = book_count;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<Integer, Person> getReaders() {
        return readers;
    }

    public void setReaders(Map<Integer, Person> readers) {
        this.readers = readers;
    }

    public Map<Integer, Librarian> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Librarian> employees) {
        this.employees = employees;
    }

    public List<Book> getCollection() {
        return collection;
    }

    public void setCollection(List<Book> collection) {
        this.collection = collection;
    }

    public List<Borrow> getBorrow_history() {
        return borrow_history;
    }

    public void setBorrow_history(List<Borrow> borrow_history) {
        this.borrow_history = borrow_history;
    }

    @Override
    public String toString() {
        return "Library{" +
                "library_id=" + library_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", book_count=" + book_count +
                '}';
    }

}
