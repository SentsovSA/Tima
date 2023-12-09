package Lab2.Interface;

public class Main {
    public static void main(String[] args) {
        DepartmentManager departmentManager = new DepartmentManager();

        // Открытие отдела
        System.out.println(departmentManager.openDepartment("IT"));
        System.out.println(departmentManager.openDepartment("HR"));

        // Прием сотрудника
        System.out.println(departmentManager.hireEmployee("IT", "Developer", "John Doe"));

        // Информация о составе отдела
        System.out.println(departmentManager.getDepartmentInfo("IT"));

        // Перевод сотрудника в другой отдел
        System.out.println(departmentManager.transferEmployee("John Doe", "IT", "HR"));

        // Информация о составе отдела после перевода
        System.out.println(departmentManager.getDepartmentInfo("IT"));
        System.out.println(departmentManager.getDepartmentInfo("HR"));

        // Увольнение сотрудника
        System.out.println(departmentManager.dismissEmployee("John Doe", "HR"));

        System.out.println(departmentManager.getDepartmentInfo("HR"));
    }
}
