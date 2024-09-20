package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ComponentType;

import java.util.UUID;

public class Component {
    private UUID  id ;
    private String name;
    private ComponentType componentType;
    private Project project;
    private Double vatRate;
    private Integer quantity;

    public Component(String name, ComponentType componentType, Project project , Double vatRate , Integer quantity) {
        this.name = name;
        this.componentType = componentType;
        this.project = project;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", componentType=" + componentType +
                ", project=" + project +
                ", vatRate=" + vatRate +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
