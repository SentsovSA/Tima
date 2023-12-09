package Lab2.Interface;

public interface DepartmentManagement {
    String openDepartment(String departmentName);
    String hireEmployee(String departmentName, String position, String employeeName);
    String transferEmployee(String employeeName, String fromDepartment, String toDepartment);
    String dismissEmployee(String employeeName, String departmentName);
    String changeEmployeePosition(String employeeName, String newPosition);
    String getDepartmentInfo(String departmentName);
}
