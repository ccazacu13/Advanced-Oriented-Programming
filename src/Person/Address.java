package Person;

import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;

public class Address {
    private String country, city, street;
    private int postal_code;

    public Address()
    {

    }

    public Address(String country, String city, String street, int postal_code) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postal_code = postal_code;
    }

    public void read_address(Scanner scanner){

//        Scanner scanner = new Scanner(System.in);
        System.out.print("Country: ");
        this.country = scanner.nextLine();
        System.out.print("City: ");
        this.city = scanner.nextLine();
        System.out.print("Street: ");
        this.street = scanner.nextLine();

        boolean check = true;
        while(check) {
            try {
                System.out.print("Postal code: ");
                this.postal_code = Integer.parseInt(scanner.nextLine());
                check = false;
            }
            catch (NumberFormatException e)
            {
                System.out.println("This is not a valid postal code");
            }
        }

//        scanner.close();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postal_code=" + postal_code +
                '}';
    }
}
