package hariti.asmaa.ma.batiCuisine.entities;

public class Labor {
    private String type;
    private double hourlyRate;
    private int workHours;
    private double productivityFactor;

    public Labor(String type, double hourlyRate, int workHours, double productivityFactor) {
        this.type = type;
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.productivityFactor = productivityFactor;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public int getWorkHours() { return workHours; }
    public void setWorkHours(int workHours) { this.workHours = workHours; }

    public double getProductivityFactor() { return productivityFactor; }
    public void setProductivityFactor(double productivityFactor) { this.productivityFactor = productivityFactor; }

    @Override
    public String toString() {
        return "Labor{" +
                "type='" + type + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", workHours=" + workHours +
                ", productivityFactor=" + productivityFactor +
                '}';
    }
}
