package pro.sky.course2.hometask0905.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.hometask0905.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.exceptions.EmployeeStorageIsFullException;
import pro.sky.course2.hometask0905.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private static final int maxSize = 20;

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (employeeList.size() == maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        Employee newcomer = new Employee(firstName, lastName);

        if (employeeList.contains(newcomer)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeList.add(newcomer);
        return newcomer;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);

        if (!employeeList.remove(target)) {
            throw new EmployeeNotFoundException();
        }
        return target;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);

        if (employeeList.contains(target)) {
            return target;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public List<Employee> showArray() {
        return Collections.unmodifiableList(employeeList);
    }
}
