package jpapractice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class MainJPA {

    public static void main(String[] args) {

        Product product1 = new Product("Bla", 12);
        Product product2 = new Product("Ble", 10);
        Product product3 = new Product("Kek", 4);
        Product product4 = new Product("ASD", 99);
        Product product5 = new Product("Ball", 102);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();

        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);
        em.persist(product5);

        Category category1 = new Category("Random");
        Category category2 = new Category("Import");

        em.persist(category1);
        em.persist(category2);

        category1.addProductToCategory(product1);
        category1.addProductToCategory(product4);
        category2.addProductToCategory(product3);
        category2.addProductToCategory(product2);
        category2.addProductToCategory(product5);

        // Produkty z wybranej kategorii:
        System.out.println("Products in category2 - by navigation property:");
        List<Product> productsFromGivenCategory = category2.getProducts();
        for (Product prod : productsFromGivenCategory) {
            System.out.println(prod.getProductName());
        }
        // Lub wyciaganie przez zapytanie:
        System.out.println("Products in category2 - by query:");
        productsFromGivenCategory = em.createQuery("from Product as product where product.category=:category",
                Product.class).setParameter("category",category2).getResultList();
        for (Product prod : productsFromGivenCategory) {
            System.out.println(prod.getProductName());
        }

        System.out.println("Category of the product1 - Bla - by navigation property: ");
        Category categoryOfThisProduct = product1.getCategory();
        System.out.println(categoryOfThisProduct.getName());
        System.out.println("Category of the product1 - Bla - by query: ");
        categoryOfThisProduct = em.createQuery("from Product as product where product.ProductName like 'Bla'",
                Product.class).getSingleResult().getCategory();
        System.out.println(categoryOfThisProduct.getName());

        etx.commit();
        em.close();
    }

}