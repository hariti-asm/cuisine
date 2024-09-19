package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ComponentType;

public class Component {
    private String name;
    private double vatRate;
    private ComponentType componentType;

    public Component(String name, double vatRate, ComponentType componentType) {
        this.name = name;
        this.vatRate = vatRate;
        this.componentType = componentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", vatRate=" + vatRate +
                ", componentType=" + componentType +
                '}';
    }
}
