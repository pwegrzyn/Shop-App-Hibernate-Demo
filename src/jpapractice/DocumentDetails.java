package jpapractice;

import javax.persistence.Embeddable;

@Embeddable
public class DocumentDetails {

    private String InternalCode;
    private Integer CreatedCopies;
    private Boolean isDigitalOnly;

    public DocumentDetails() {}

    public DocumentDetails(String internalCode, int copies, boolean isDigitalOnly) {
        this.InternalCode = internalCode;
        this.CreatedCopies = copies;
        this.isDigitalOnly = isDigitalOnly;
    }

    public String getInternalCode() {
        return InternalCode;
    }

    public void setInternalCode(String internalCode) {
        InternalCode = internalCode;
    }

    public Integer getCreatedCopies() {
        return CreatedCopies;
    }

    public void setCreatedCopies(Integer createdCopies) {
        CreatedCopies = createdCopies;
    }

    public Boolean getDigitalOnly() {
        return isDigitalOnly;
    }

    public void setDigitalOnly(Boolean digitalOnly) {
        isDigitalOnly = digitalOnly;
    }
}
