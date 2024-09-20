package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ComponentType;

import java.util.UUID;

public class  Materiel extends Component {
    private UUID id;
    private double quantity;
    private double unitCost;
    private double transportationCost;
    private double qualityCoefficient;

    public Materiel(String name,  ComponentType componentType, double quantity, double unitCost, double transportationCost, double qualityCoefficient) {
        super(name,  componentType);
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.transportationCost = transportationCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public double getQuantity() { return quantity; }
public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

    public double getTransportationCost() { return transportationCost; }
    public void setTransportationCost(double transportationCost) { this.transportationCost = transportationCost; }

    public double getQualityCoefficient() { return qualityCoefficient; }
    public void setQualityCoefficient(double qualityCoefficient) { this.qualityCoefficient = qualityCoefficient; }

    @Override
    public String toString() {
        return "Materiel{" +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", transportationCost=" + transportationCost +
                ", qualityCoefficient=" + qualityCoefficient +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
