package jpapractice;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    protected DocumentDetails DocumentDetails;

    public Document() {}

    public Document(String internalCode, int copies, boolean isDigitalOnly) {
        DocumentDetails = new DocumentDetails(internalCode, copies, isDigitalOnly);
    }

    public jpapractice.DocumentDetails getDocumentDetails() {
        return DocumentDetails;
    }

    public void setDocumentDetails(jpapractice.DocumentDetails documentDetails) {
        DocumentDetails = documentDetails;
    }
}
