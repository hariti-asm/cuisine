package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ProjectState;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Project {
    private UUID id;
    private String name;
    private double surfaceArea;
    private Double totalCost;
    private  Double margin;
    private ProjectState projectState;
    private Optional<Client> client;
    private List<Component> components;
    private Estimate estimate; ;

    public Project(UUID id, String name, double surfaceArea, Double vatRate, Double totalCost, Double margin, ProjectState projectState, Optional<Client> client, List<Component> components, Estimate estimate) {
        this.id = id;
        this.name = name;
        this.surfaceArea = surfaceArea;
        this.totalCost = totalCost;
        this.margin = margin;
        this.projectState = projectState;
        this.client = client;
        this.components = components;
        this.estimate = estimate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSurfaceArea() { return surfaceArea; }
    public void setSurfaceArea(double surfaceArea) { this.surfaceArea = surfaceArea; }

    public ProjectState getProjectState() { return projectState; }
    public void setProjectState(ProjectState projectState) { this.projectState = projectState; }

    public Estimate getEstimate() { return estimate; }
    public void setEstimate(Estimate estimate) { this.estimate = estimate; }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", totalCost=" + totalCost +
                ", margin=" + margin +
                ", projectState=" + projectState +
                ", estimate=" + estimate +
                '}';
    }

    public Optional<Client> getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = Optional.ofNullable(client);
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
