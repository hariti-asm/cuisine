package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ComponentType;

public class Component {
    private String name;
    private ComponentType componentType;
    public Component(String name, ComponentType componentType) {
        this.name = name;
        this.componentType = componentType;
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

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", componentType=" + componentType +
                '}';
    }
}
