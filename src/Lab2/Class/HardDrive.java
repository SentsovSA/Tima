package Lab2.Class;
/*Подкласс - Жесткий диск (Модель,
Цена, Дата выхода на рынок).*/

import java.util.Date;

public class HardDrive extends ExMemory{
    private HardDriveType type;
    private String model;
    private double cost;
    private Date launchDate;

    public HardDrive(String manufacturer, double capacity, Date accessTime, HardDriveType type, String model, double cost, Date launchDate) {
        super(manufacturer, capacity, accessTime);
        this.type = type;
        this.model = model;
        this.cost = cost;
        this.launchDate = launchDate;
    }

    public HardDriveType getType() {
        return type;
    }

    public void setType(HardDriveType type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    @Override
    public String toString() {
        return "Жесткий диск: " +
                "производитель = '" + super.getManufacturer() + '\'' +
                ", ёмкость = " + super.getCapacity() +
                ", время доступа = " + dateFormat.format(super.getAccessTime()) +
                ", тип " + type +
                ", модель '" + model + '\'' +
                ", цена " + cost +
                ", дата выпуска на рынок " + ExMemory.dateFormat.format(launchDate);
    }


}
