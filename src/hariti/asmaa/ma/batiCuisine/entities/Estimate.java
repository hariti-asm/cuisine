package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.EstimateStatus;

import java.time.LocalDate;

public class Estimate {
    private LocalDate issueDate;
    private LocalDate validityDate;
    private EstimateStatus status;

    public Estimate( LocalDate issueDate, LocalDate validityDate, EstimateStatus status) {
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.status = status;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public EstimateStatus getStatus() {
        return status;
    }

    public void setStatus(EstimateStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Estimate{" +
                ", issueDate=" + issueDate +
                ", validityDate=" + validityDate +
                ", status=" + status +
                '}';
    }
}
