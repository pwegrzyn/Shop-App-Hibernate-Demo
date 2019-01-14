package jpapractice;

import org.apache.derby.client.am.DateTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice extends Document{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String InvoiceNumber;
    private Integer Quantity;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> includedProducts;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CUSTOMER_FK")
    private Customer customer;

    private Date issueDate;

    public Invoice() {}

    public Invoice(String invoiceNumber, Integer quantity) {
        this.InvoiceNumber = invoiceNumber;
        this.Quantity = quantity;
        this.includedProducts = new HashSet<>();
        this.issueDate = new Date();
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Set<Product> getIncludedProducts() {
        return includedProducts;
    }

    public void setIncludedProducts(Set<Product> includedProducts) {
        this.includedProducts = includedProducts;
    }

    public void addToIncludedProducts(Product prod) {
        this.includedProducts.add(prod);
        this.Quantity++;
        if(!prod.getCanBeSoldIn().contains(this)) {
            prod.getCanBeSoldIn().add(this);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if(!customer.getInvoices().contains(this)) {
            customer.getInvoices().add(this);
        }
    }

    public Date getIssueDate() {
        return this.issueDate;
    }
}
