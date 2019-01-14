package jpapractice;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier extends Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany()
    @JoinColumn(name="SUPPLIER_FK")
    private Set<Product> suppliedProducts;

    private String bankAccountNumber;

    public Supplier() {}

    public Supplier(String CompanyName, String Street, String City, String ZipCode, String bankAccountNumber) {
        super(CompanyName, Street, City, ZipCode);
        this.suppliedProducts = new HashSet<>();
        this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public void presentInfo() {
        System.out.println("Presenting info for Supplier: ");
        System.out.println("Company Name: " + CompanyName);
        System.out.println("City: " + City);
        System.out.println("Bank Account Number: " + this.bankAccountNumber);
    }

    public Set<Product> getSuppliedProducts() {
        return suppliedProducts;
    }

    public void setSuppliedProducts(Set<Product> suppliedProducts) {
        this.suppliedProducts = suppliedProducts;
    }

    public void addProductToSupplied(Product prod) {
        this.suppliedProducts.add(prod);
        if(prod.getSupplier() == null) {
            prod.setSupplier(this);
        }
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}
