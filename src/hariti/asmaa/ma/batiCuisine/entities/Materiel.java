package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ComponentType;

public class Materiel extends Component {
    private double unitCost;
    private double quantity;
    private double transportCost;
    private double qualityCuff;

    public Materiel(String name, double vatRate, ComponentType componentType) {
        super(name, vatRate, componentType);
    }


    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getQualityCuff() {
        return qualityCuff;
    }

    public void setQualityCuff(double qualityCuff) {
        this.qualityCuff = qualityCuff;
    }

    @Override
    public String toString() {
        return "Materiel{" +
                "unitCost=" + unitCost +
                ", quantity=" + quantity +
                ", transportCost=" + transportCost +
                ", qualityCuff=" + qualityCuff +
                '}';
    }
}
