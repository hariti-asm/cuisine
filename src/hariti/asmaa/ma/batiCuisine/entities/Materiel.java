package hariti.asmaa.ma.batiCuisine.entities;

public class Materiel {
    private String name;
    private int quantity;
    private double unitCost;
    private double transportationCost;
    private double qualityCoefficient;

    public Materiel(String name, int quantity, double unitCost, double transportationCost, double qualityCoefficient) {
        this.name = name;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.transportationCost = transportationCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

    public double getTransportationCost() { return transportationCost; }
    public void setTransportationCost(double transportationCost) { this.transportationCost = transportationCost; }

    public double getQualityCoefficient() { return qualityCoefficient; }
    public void setQualityCoefficient(double qualityCoefficient) { this.qualityCoefficient = qualityCoefficient; }

    @Override
    public String toString() {
        return "Materiel{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", transportationCost=" + transportationCost +
                ", qualityCoefficient=" + qualityCoefficient +
                '}';
    }
}
