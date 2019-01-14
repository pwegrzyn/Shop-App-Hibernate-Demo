package jpapractice;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int CategoryID;
    private String Name;

    @OneToMany()
    @JoinColumn(name="CATEGORY_FK")
    private List<Product> products;

    public Category() {}

    public Category(String name) {
        Name = name;
        this.products = new LinkedList<>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProductToCategory(Product prod) {
        this.products.add(prod);
        if(prod.getCategory() == null) {
            prod.setCategory(this);
        }
    }
}
