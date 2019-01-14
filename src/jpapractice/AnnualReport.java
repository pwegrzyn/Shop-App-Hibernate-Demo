package jpapractice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnnualReport extends Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Integer ForYear;
    private Boolean Confirmed;

    public AnnualReport() {}

    public AnnualReport(String year, boolean isConfirmed) throws IllegalArgumentException {
        Integer parsedYear = Integer.parseInt(year);
        if(parsedYear > LocalDateTime.now().getYear() || parsedYear < 1990) {
            throw new IllegalArgumentException("Provided year is invalid");
        } else {
            this.ForYear = parsedYear;
        }
        this.Confirmed = isConfirmed;
    }

    public Integer getForYear() {
        return ForYear;
    }

    public void setForYear(Integer year) {
        if(year > LocalDateTime.now().getYear() || year < 1990) {
            throw new IllegalArgumentException("Provided year is invalid");
        } else {
            this.ForYear = year;
        }
    }

    public Boolean getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        Confirmed = confirmed;
    }
}
