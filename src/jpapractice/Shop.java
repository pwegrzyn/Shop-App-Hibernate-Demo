package jpapractice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;


public class Shop {

    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Product product1 = new Product("Bla", 12);
        Product product2 = new Product("Ble", 10);
        Product product3 = new Product("Kek", 4);
        Product product4 = new Product("ASD", 99);
        Product product5 = new Product("Ball", 102);

        session.save(product1);
        session.save(product2);
        session.save(product3);
        session.save(product4);
        session.save(product5);

        Supplier supplier = new Supplier("TestCompany", "Krolewska", "Krakow", "32-850", "123");
        session.save(supplier);
        supplier.addProductToSupplied(product1);
        supplier.addProductToSupplied(product2);
        supplier.addProductToSupplied(product3);
        supplier.addProductToSupplied(product4);
        supplier.addProductToSupplied(product5);

        Category category1 = new Category("Random");
        Category category2 = new Category("Import");

        session.save(category1);
        session.save(category2);

        category1.addProductToCategory(product1);
        category1.addProductToCategory(product4);
        category2.addProductToCategory(product3);
        category2.addProductToCategory(product2);
        category2.addProductToCategory(product5);

        Customer customer1 = new Customer("customer1", "Krolewska", "Krakow", "12-345", 20.00);
        Customer customer2 = new Customer("customer2", "Nawojki", "Krakow", "12-345", 15.00);

        session.save(customer1);
        session.save(customer2);

        tx.commit();
        session.close();

        InvoiceManager invoiceManager = new InvoiceManager();
        String generatedInvoiceNumber = invoiceManager.createInvoiceForCustomer(customer1, Arrays.asList(product1, product2, product3));

        Invoice foundInvoice = invoiceManager.findInvoiceByNumber(generatedInvoiceNumber);

        invoiceManager.presentInvoice(foundInvoice);

        int invoicesWithMoreThan2Products = invoiceManager.findAllInvoicesWithQuantityMoreThan(2).size();
        int invoicesWith10Products = invoiceManager.findAllInvoicesWithQuantity(10).size();
        System.out.println("Found " + invoicesWithMoreThan2Products + " invoices with more than 2 prodcuts in the DB.");
        System.out.println("Found " + invoicesWith10Products + " invoices with 10 prodcuts in the DB.");
        System.out.println("The average quantity of products in invoices is: " + invoiceManager.calculateAvgQuantity());

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}