package Lab2.Class;

//Вывести список дисков заданной емкости.
//Подсчитать среднюю цену накопителей заданного производителя.

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws ParseException {
        ExMemory[] drives = new ExMemory[10];
        for (int i = 0; i < 5; i++) {
            drives[i] = new ExMemory("Manufacturer" + i, 512 + i * 8, ExMemory.dateFormat.parse("1111-11-11, 11:11:11"));
        }
        for (int i = 5; i < 10; i++) {
            drives[i] = new HardDrive("Manufacturer" + i, 512 + i * 8, ExMemory.dateFormat.parse("1111-11-11, 11:11:11"),
                    new HardDriveType(i, "Name " + i), "Model" + i, 1000*i, ExMemory.dateFormat.parse("1111-11-11, 11:11:11"));
        }
        for (ExMemory drive: drives) {
            System.out.println(drive.toString());
        }
        displayingDrives(drives);
        circulationAvgCost(drives);
    }

    public static void displayingDrives(ExMemory[] drives) {
        boolean flag = false;
        System.out.print("Напишите необходимую емкость: ");
        int cap = scan.nextInt();
        for (ExMemory drive : drives) {
            if (drive.getCapacity() == cap && drive instanceof HardDrive) {
                System.out.println(drive);
                flag = true;
            }
        }
        if(!flag) System.out.println("Такой ёмкости дисков нет");
    }

    public static void circulationAvgCost(ExMemory[] drives) {
        System.out.print("Напишите необходимого производителя: ");
        String manrer = scan.next();
        double sum = 0;
        int counter = 0;
        boolean flag = false;
        for (ExMemory drive : drives) {
                if(drive.getManufacturer().equals(manrer) && drive instanceof HardDrive) {
                    counter++;
                    sum += ((HardDrive) drive).getCost();
                    flag = true;
            }
        }
        sum = sum / counter;
        if(flag) {
            System.out.println("Средняя стоимость дисков производителя " + manrer + " равна " + sum);
        } else {
            System.out.println("Дисков заданного производителя нет в наличии ");
        }

    }
}
