package Lab2.Interface;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class DepartmentManager implements DepartmentManagement{
    private HashMap<String, HashMap<String, Employee>> departments = new HashMap<>();

    @Override
    public String openDepartment(String departmentName) {
        if (!departments.containsKey(departmentName)) {
            departments.put(departmentName, new HashMap<>());
            return "Департамент открыт успешно";
        }
        return "Возможно такой департамент уже существует";
    }

    @Override
    public String hireEmployee(String departmentName, String position, String employeeName) {
        if (departments.containsKey(departmentName)) {
            Employee employee = new Employee(employeeName, position);
            departments.get(departmentName).put(employeeName, employee);
            return "Сотрудник по имени " + employeeName +" нанят";
        }
        return "Сотрудник не принят на работу";
    }

    @Override
    public String transferEmployee(String employeeName, String fromDepartment, String toDepartment) {
        if (departments.containsKey(fromDepartment) && departments.containsKey(toDepartment)) {
            if (departments.get(fromDepartment).containsKey(employeeName)) {
                Employee employee = departments.get(fromDepartment).remove(employeeName);
                departments.get(toDepartment).put(employeeName, employee);
                return "Сотрудника по имени " + employeeName +" перевели в департамент " + toDepartment;
            }
        }
        return "Сотрудник не был переведен";
    }

    @Override
    public String dismissEmployee(String employeeName, String departmentName) {
        if (departments.containsKey(departmentName)) {
            if (departments.get(departmentName).remove(employeeName) != null) {
                return "Сотрудник уволен";
            }
        }
        return "Сотрудник не уволен";
    }

    @Override
    public String changeEmployeePosition(String employeeName, String newPosition) {
        for (HashMap<String, Employee> department : departments.values()) {
            if (department.containsKey(employeeName)) {
                department.get(employeeName).getPosition();
                return "Сотрудник переведен";
            }
        }
        return "Неудача";
    }


    @Override
    public String getDepartmentInfo(String departmentName) {
        if (departments.containsKey(departmentName)) {
            return departmentName + " = " + departments.get(departmentName).toString();
        }
        return "Департамент не найден";
    }
}
