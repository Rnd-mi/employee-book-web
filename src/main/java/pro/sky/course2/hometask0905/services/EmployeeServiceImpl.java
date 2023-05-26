package pro.sky.course2.hometask0905.services;

import org.springframework.stereotype.Service;
import pro.sky.course2.hometask0905.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.exceptions.EmployeeStorageIsFullException;
import pro.sky.course2.hometask0905.model.Employee;

import java.util.*;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap = new HashMap<>();
    private static final int maxSize = 20;

    public Map<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {

        if (employeeMap.size() == maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        Employee newcomer = new Employee(firstName, lastName, department, salary);

        if (employeeMap.containsKey(newcomer.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(newcomer.getFullName(), newcomer);
        return newcomer;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;

        if (!employeeMap.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.remove(fullName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;

        if (employeeMap.containsKey(fullName)) {
            return employeeMap.get(fullName);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> showArray() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }
}

