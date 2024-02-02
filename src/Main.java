import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Company {
    private String name;
    private List<Department> departments;

    public Company(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    public void removeDepartment(Department department) {
        this.departments.remove(department);
    }

    public Employee findEmployeeByName(String fullName) {
        for (Department department : departments) {
            for (Employee employee : department.getEmployees()) {
                if (employee.getFullName().equals(fullName)) {
                    return employee;
                }
            }
        }
        return null;
    }
}

class Department {
    private String name;
    private Company company;
    private List<Employee> employees;

    public Department(String name, Company company) {
        this.name = name;
        this.company = company;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }

    public int getNumberOfEmployees() {
        return this.employees.size();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(this.employees);
    }

    public String getName() {
        return name;
    }
}
class Employee {
    private String fullName;
    private String position;
    private Department department;

    public Employee(String fullName, String position, Department department) {
        this.fullName = fullName;
        this.position = position;
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public Department getDepartment() {
        return department;
    }
}

public class Main {
    public static void main(String[] args) {
        Company company = new Company("StudioX");

        Department directorDepartment = new Department("Director", company);
        Department chiefDepartment = new Department("Chief", company);

        company.addDepartment(directorDepartment);
        company.addDepartment(chiefDepartment);

        Employee employee1 = new Employee("Erda", "Director (General Director, Manager) of the enterprise", directorDepartment);
        Employee employee2 = new Employee("Mukha", "Financial Director (Deputy Director for Finance)", directorDepartment);
        Employee employee3 = new Employee("Era", "Chief Accountant", chiefDepartment);
        Employee employee4 = new Employee("Arman", "Chief Dispatcher", chiefDepartment);
        Employee employee5 = new Employee("Amir", "Chief Engineer", chiefDepartment);
        Employee employee6 = new Employee("Islam", "Chief Designer", chiefDepartment);


        directorDepartment.addEmployee(employee1);
        directorDepartment.addEmployee(employee2);
        chiefDepartment.addEmployee(employee3);
        chiefDepartment.addEmployee(employee4);
        chiefDepartment.addEmployee(employee5);
        chiefDepartment.addEmployee(employee6);

        System.out.println("Number of employees in Director Department: " + directorDepartment.getNumberOfEmployees());
        System.out.println("Employees in Director Department:");
        for (Employee employee : directorDepartment.getEmployees()) {
            System.out.println(employee.getFullName() + " - " + employee.getPosition());
        }

        System.out.println("Number of employees in Chief Department: " + chiefDepartment.getNumberOfEmployees());
        System.out.println("Employees in Chief Department:");
        for (Employee employee : chiefDepartment.getEmployees()) {
            System.out.println(employee.getFullName() + " - " + employee.getPosition());
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the full name of the employee to search: ");
            String searchFullName = scanner.nextLine();

            Employee foundEmployee = company.findEmployeeByName(searchFullName);

            if (foundEmployee != null) {
                System.out.println("Employee found: " + foundEmployee.getFullName() +
                        " works in the " + foundEmployee.getDepartment().getName() + " department.");
            } else {
                System.out.println("Employee with full name '" + searchFullName + "' not found.");
            }
        }
    }
}