package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ProjectState;

import java.util.List;

public class Project {
    private String name;
    private double surfaceArea;
    private ProjectState projectState;
    private List<Materiel> materials;
    private List<Labor> labor;
    private Estimate estimate;

    public Project(String name, double surfaceArea, ProjectState projectState) {
        this.name = name;
        this.surfaceArea = surfaceArea;
        this.projectState = projectState;
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
}
