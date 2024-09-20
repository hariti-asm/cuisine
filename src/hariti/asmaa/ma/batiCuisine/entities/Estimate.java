package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.EstimateStatus;

import java.time.LocalDate;

public class Estimate {
    private LocalDate issueDate;
    private LocalDate validityDate;
    private EstimateStatus status;
    private Project project;
    public Estimate( Project project , LocalDate issueDate, LocalDate validityDate, EstimateStatus status) {
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.status = status;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
