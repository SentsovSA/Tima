package Lab2.Interface;

import java.util.HashMap;

public class DepartmentManager implements DepartmentManagement{
    private HashMap<String, HashMap<String, Employee>> departments = new HashMap<>();

    @Override
    public boolean openDepartment(String departmentName) {
        if (!departments.containsKey(departmentName)) {
            departments.put(departmentName, new HashMap<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean hireEmployee(String departmentName, String position, String employeeName) {
        if (departments.containsKey(departmentName)) {
            Employee employee = new Employee(employeeName, position);
            departments.get(departmentName).put(employeeName, employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferEmployee(String employeeName, String fromDepartment, String toDepartment) {
        if (departments.containsKey(fromDepartment) && departments.containsKey(toDepartment)) {
            if (departments.get(fromDepartment).containsKey(employeeName)) {
                Employee employee = departments.get(fromDepartment).remove(employeeName);
                departments.get(toDepartment).put(employeeName, employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dismissEmployee(String employeeName, String departmentName) {
        if (departments.containsKey(departmentName)) {
            return departments.get(departmentName).remove(employeeName) != null;
        }
        return false;
    }

    @Override
    public boolean changeEmployeePosition(String employeeName, String newPosition) {
        for (HashMap<String, Employee> department : departments.values()) {
            if (department.containsKey(employeeName)) {
                department.get(employeeName).getPosition();
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDepartmentInfo(String departmentName) {
        if (departments.containsKey(departmentName)) {
            return departments.get(departmentName).toString();
        }
        return "Department not found";
    }
}
