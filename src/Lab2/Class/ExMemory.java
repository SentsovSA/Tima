package Lab2.Class;

/*Базовый класс – Внешняя память (Производитель, Емкость,
Время доступа).*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExMemory {
    private String manufacturer;
    private double capacity;
    private Date accessTime;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");

    public ExMemory(String manufacturer, double capacity, Date accessTime) {
        this.manufacturer = manufacturer;
        this.capacity = capacity;
        this.accessTime = accessTime;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        return "Внешняя память: " +
                "производитель = '" + manufacturer + '\'' +
                ", ёмкость = " + capacity +
                ", время доступа = " + dateFormat.format(accessTime);
    }
}
