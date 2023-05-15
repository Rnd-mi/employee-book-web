package pro.sky.course2.hometask0905.service;

import pro.sky.course2.hometask0905.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Collection<Employee> showArray();
}
