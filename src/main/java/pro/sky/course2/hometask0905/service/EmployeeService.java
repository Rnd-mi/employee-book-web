package pro.sky.course2.hometask0905.service;

import pro.sky.course2.hometask0905.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    List<Employee> showArray();
}
