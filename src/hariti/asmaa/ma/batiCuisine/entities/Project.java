package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ProjectState;

import java.util.UUID;

public class Project {
    private String nameProject;
    private double margin;
    private double totalCost;
    private double surfaceArea;
    private ProjectState state;
public Project() {}
    public Project(String nameProject, double margin, double totalCost, double surfaceArea, ProjectState state) {
        this.nameProject = nameProject;
        this.margin = margin;
        this.totalCost = totalCost;
        this.surfaceArea = surfaceArea;
        this.state = state;
    }



    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public ProjectState getState() {
        return state;
    }

    public void setState(ProjectState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Project{" +
                "nameProject='" + nameProject + '\'' +
                ", margin=" + margin +
                ", totalCost=" + totalCost +
                ", surfaceArea=" + surfaceArea +
                ", state=" + state +
                '}';
    }
}
