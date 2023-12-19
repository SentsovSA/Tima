package Lab6;

import Lab2.Class.HardDrive;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DriveTableModel extends AbstractTableModel {
    ArrayList<HardDrive> drives;

    public DriveTableModel(ArrayList<HardDrive> drives) {
        super();
        this.drives = drives;
    }

    @Override
    public int getRowCount() {
        return drives.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    //String manufacturer, double capacity, Date accessTime, HardDriveType type(kod, name), String model, double cost, Date launchDate
    @Override
    public String getColumnName(int c) {
        return switch (c) {
            case 0 -> "Производитель";
            case 1 -> "Ëмкость";
            case 2 -> "Время доступа";
            case 3 -> "Код";
            case 4 -> "Название";
            case 5 -> "Модель";
            case 6 -> "Стоимость";
            case 7 -> "Дата запуска";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int r, int c) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
        return switch (c) {
            case 0 -> drives.get(r).getManufacturer();
            case 1 -> drives.get(r).getCapacity();
            case 2 -> formatter.format(drives.get(r).getAccessTime());
            case 3 -> drives.get(r).getType().getKod();
            case 4 -> drives.get(r).getType().getName();
            case 5 -> drives.get(r).getModel();
            case 6 -> drives.get(r).getCost();
            case 7 -> drives.get(r).getLaunchDate();
            default -> "";
        };
    }

}
