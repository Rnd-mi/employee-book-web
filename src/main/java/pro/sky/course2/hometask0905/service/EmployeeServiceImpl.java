package pro.sky.course2.hometask0905.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.hometask0905.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.exceptions.EmployeeStorageIsFullException;
import pro.sky.course2.hometask0905.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap = new HashMap<>();
    private static final int maxSize = 20;

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (employeeMap.size() == maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        Employee newcomer = new Employee(firstName, lastName);

        if (employeeMap.containsKey(newcomer.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(newcomer.getFullName(), newcomer);
        return newcomer;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);

        if (!employeeMap.containsKey(target.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.remove(target.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);

        if (employeeMap.containsKey(target.getFullName())) {
            return employeeMap.get(target.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> showArray() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }
}
