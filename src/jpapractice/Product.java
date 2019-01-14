package jpapractice;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ProductName;
    private Integer UnitsOnStock;

    @ManyToOne
    @JoinColumn(name="SUPPLIER_FK")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name="CATEGORY_FK")
    private Category category;

    @ManyToMany(mappedBy = "includedProducts", cascade = CascadeType.ALL)
    private Set<Invoice> canBeSoldIn;

    public Product() {}

    public Product(String name, int unitsOnStock) {
        this.ProductName = name;
        this.UnitsOnStock = unitsOnStock;
        this.canBeSoldIn = new HashSet<>();
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getUnitsOnStock() {
        return UnitsOnStock;
    }

    public void setUnitsOnStock(Integer unitsOnStock) {
        UnitsOnStock = unitsOnStock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        if(!this.supplier.getSuppliedProducts().contains(this)) {
            this.supplier.getSuppliedProducts().add(this);
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        if(!this.category.getProducts().contains(this)) {
            this.category.getProducts().add(this);
        }
    }

    public Set<Invoice> getCanBeSoldIn() {
        return canBeSoldIn;
    }

    public void setCanBeSoldIn(Set<Invoice> canBeSoldIn) {
        this.canBeSoldIn = canBeSoldIn;
    }

    public void addToCanBeSoldIn(Invoice invoice) {
        this.canBeSoldIn.add(invoice);
        this.UnitsOnStock--;
        if(!invoice.getIncludedProducts().contains(this)) {
            invoice.getIncludedProducts().add(this);
        }
    }
}
