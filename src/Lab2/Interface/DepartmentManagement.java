package Lab2.Interface;

public interface DepartmentManagement {
    boolean openDepartment(String departmentName);
    boolean hireEmployee(String departmentName, String position, String employeeName);
    boolean transferEmployee(String employeeName, String fromDepartment, String toDepartment);
    boolean dismissEmployee(String employeeName, String departmentName);
    boolean changeEmployeePosition(String employeeName, String newPosition);
    String getDepartmentInfo(String departmentName);
}
