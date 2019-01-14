package jpapractice;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class InvoiceManager {

    public String createInvoiceForCustomer(Customer customer, List<Product> productsToBuy) {

        Session session = Shop.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Random random = new Random();
        Integer invoiceNumber = random.nextInt();
        Invoice newInvoice = new Invoice(invoiceNumber.toString(), 0);

        session.save(newInvoice);

        for(Product prod : productsToBuy) {
            newInvoice.addToIncludedProducts(prod);
        }

        customer.addNewInvoice(newInvoice);

        tx.commit();
        session.close();

        return invoiceNumber.toString();

    }

    public void presentInvoice(Invoice invoice) {

        if(invoice == null) {
            return;
        }

        System.out.println("##############################################");
        System.out.println("*** INVOICE - " + invoice.getInvoiceNumber() + " ***");
        System.out.println("    Issue Date: " + invoice.getIssueDate().toString());
        System.out.println("    Quanttiy of products: " + invoice.getQuantity());
        System.out.println("    Customer: " + invoice.getCustomer().getCompanyName());
        System.out.println("    Inlucded Products: ");
        for(Product prod : invoice.getIncludedProducts()) {
            System.out.println("        - " + prod.getProductName());
        }
        System.out.println("##############################################");

    }

    public Invoice findInvoiceByNumber(String invoiceNumber) {

        Session session = Shop.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Invoice foundInvoice = null;
        try {
            foundInvoice = session.createQuery(
                    "from Invoice as invoice where invoice.InvoiceNumber like :invNum", Invoice.class)
                    .setParameter("invNum", invoiceNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("There's no invoice with number " + invoiceNumber + " in the database");
        }

        tx.commit();
        session.close();

        return foundInvoice;

    }

    public List<String> findAllInvoicesWithQuantityMoreThan(int quantity) {

        List<String> invoices = new LinkedList<>();

        Session session = Shop.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        invoices = session.createSQLQuery("select InvoiceNumber from INVOICE where quantity > :qnt")
                .setParameter("qnt", quantity)
                .getResultList();

        tx.commit();
        session.close();

        return invoices;

    }

    public List findAllInvoicesWithQuantity(int quantity) {

        Session session = Shop.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createNamedQuery("findInvoicesWithQnt").setParameter("qnt", quantity);

        List resultList = query.getResultList();

        tx.commit();
        session.close();

        return resultList;

    }

    public double calculateAvgQuantity() {

        Session session = Shop.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        double averageQuantity = (double)session.createQuery(
                "select avg(invoice.Quantity) from Invoice as invoice")
                .getSingleResult();

        tx.commit();
        session.close();

        return averageQuantity;

    }

}
