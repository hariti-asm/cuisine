package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ProjectState;

import java.util.List;
import java.util.UUID;

public class Project {
    private UUID id;
    private String name;
    private double surfaceArea;
    private Double vatRate;
    private  double margin;
    private ProjectState projectState;
    private List<Materiel> materials;
    private List<Labor> labor;
    private Estimate estimate;

    public Project( UUID id ,String name, double surfaceArea, ProjectState projectState , Double vatRate , double margin, Estimate estimate ) {
        this.id = id;
        this.name = name;
        this.surfaceArea = surfaceArea;
        this.projectState = projectState;
        this.vatRate = vatRate;
        this.margin = margin;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSurfaceArea() { return surfaceArea; }
    public void setSurfaceArea(double surfaceArea) { this.surfaceArea = surfaceArea; }

    public ProjectState getProjectState() { return projectState; }
    public void setProjectState(ProjectState projectState) { this.projectState = projectState; }

    public List<Materiel> getMaterials() { return materials; }
    public void setMaterials(List<Materiel> materials) { this.materials = materials; }

    public List<Labor> getLabor() { return labor; }
    public void setLabor(List<Labor> labor) { this.labor = labor; }

    public Estimate getEstimate() { return estimate; }
    public void setEstimate(Estimate estimate) { this.estimate = estimate; }

    public void addMaterial(Materiel material) { this.materials.add(material); }
    public void addLabor(Labor labor) { this.labor.add(labor); }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", vatRate=" + vatRate +
                ", margin=" + margin +
                ", projectState=" + projectState +
                ", materials=" + materials +
                ", labor=" + labor +
                ", estimate=" + estimate +
                '}';
    }
}
