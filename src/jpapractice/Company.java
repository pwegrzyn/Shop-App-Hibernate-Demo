package jpapractice;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    protected String CompanyName;
    protected String Street;
    protected String City;
    protected String ZipCode;

    public Company() {}

    public Company(String companyName, String street, String city, String zipCode) {
        CompanyName = companyName;
        Street = street;
        City = city;
        ZipCode = zipCode;
    }

    abstract void presentInfo();

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

}
