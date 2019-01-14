package jpapractice;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double discount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="CUSTOMER_FK")
    private Set<Invoice> invoices;

    public Customer() {}

    public Customer(String CompanyName, String Street, String City, String ZipCode, double discount) {
        super(CompanyName, Street, City, ZipCode);
        this.discount = discount;
        this.invoices = new HashSet<>();
    }

    @Override
    public void presentInfo() {
        System.out.println("Presenting info for Customer: ");
        System.out.println("Company Name: " + CompanyName);
        System.out.println("City: " + City);
        System.out.println("Discount: " + this.discount);
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addNewInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        if(invoice.getCustomer() == null) {
            invoice.setCustomer(this);
        }
    }
}
