package jpapractice;

import javax.persistence.*;

@Embeddable
public class Address {

    private String City;
    private String Street;
    private String ZipCode;

    public Address() {}

    public Address(String city, String street, String zipCode) {
        City = city;
        Street = street;
        ZipCode = zipCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }
}
