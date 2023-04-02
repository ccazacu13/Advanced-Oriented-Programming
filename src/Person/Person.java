package Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public abstract class Person {

    private static int id;

    private final int reader_id;

    private String first_name, last_name, CNP, phone;
    private Date birth_date;
    private Address address;
    public Person(){
        this.reader_id = id++;
    }

    protected abstract void method();
    public Person(String first_name, String last_name, String CNP, String phone, Date birth_date, Address address) {
        this.reader_id = id++;
        this.first_name = first_name;
        this.last_name = last_name;
        this.CNP = CNP;
        this.phone = phone;
        this.birth_date = birth_date;
        this.address = address;
    }

    public void read_person(Scanner scanner){
//        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("First name: ");
        this.first_name = scanner.nextLine();
        System.out.print("Last name: ");
        this.last_name = scanner.nextLine();
        System.out.print("CNP: ");
        this.CNP = scanner.nextLine();
        System.out.print("Phone: ");
        this.phone = scanner.nextLine();

        boolean check = true;

        while(check) {
            try{
            System.out.print("Birth date: ");
            String strDate = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.birth_date = dateFormat.parse(strDate);
            check = false;
            }
            catch (ParseException e)
            {
                System.out.println("Date format is invalid!");
            }
        }

        System.out.println("Address: ");
        this.address = new Address();
        this.address.read_address(scanner);

//        scanner.close();
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return
                "reader_id=" + reader_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", CNP='" + CNP + '\'' +
                ", phone='" + phone + '\'' +
                ", birth_date=" + birth_date +
                ", address=" + address;
    }
}
