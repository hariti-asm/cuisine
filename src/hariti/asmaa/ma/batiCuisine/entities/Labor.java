package hariti.asmaa.ma.batiCuisine.entities;

import hariti.asmaa.ma.batiCuisine.enums.ComponentType;

public   class Labor  extends Component{
    private double hourlyRate;
    private int workHours;
    private double productivityFactor;

    public Labor(String name, ComponentType componentType,Project project, Double vatRate, Integer quantity,Double hourlyRate, int workHours, double productivityFactor) {
        super(name, componentType , project , vatRate, quantity) ;
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.productivityFactor = productivityFactor;
    }
    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public int getWorkHours() { return workHours; }
    public void setWorkHours(int workHours) { this.workHours = workHours; }

    public double getProductivityFactor() { return productivityFactor; }
    public void setProductivityFactor(double productivityFactor) { this.productivityFactor = productivityFactor; }

    @Override
    public String toString() {
        return "Labor{" +
                ", hourlyRate=" + hourlyRate +
                ", workHours=" + workHours +
                ", productivityFactor=" + productivityFactor +
                '}';
    }
}
