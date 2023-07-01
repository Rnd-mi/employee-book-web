package pro.sky.course2.hometask0905.services;

import org.springframework.stereotype.Service;
import pro.sky.course2.hometask0905.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.exceptions.EmployeeStorageIsFullException;
import pro.sky.course2.hometask0905.model.Employee;

import static pro.sky.course2.hometask0905.utility.StringHandler.nameHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap = new HashMap<>();
    private static final int maxSize = 20;

    {
        employeeMap.put("Vasiliy Konev", new Employee("Vasiliy", "Konev", 3, 25_800));
        employeeMap.put("Ivan Ivanov", new Employee("Ivan", "Ivanov", 1, 36_000));
        employeeMap.put("Evgeniy Stariy", new Employee("Evgeniy", "Stariy", 1, 50_250));
        employeeMap.put("Vladimir Kryshka", new Employee("Vladimir", "Kryshka", 3, 89_000));
        employeeMap.put("Egor Ziryanov", new Employee("Egor", "Ziryanov", 2, 55_600));
        employeeMap.put("Anna Svetova", new Employee("Anna", "Svetova", 8, 65_000));
        employeeMap.put("Ignat Ivanov", new Employee("Ignat", "Ivanov", 5, 75_100));
        employeeMap.put("James Milner", new Employee("James", "Milner", 1, 150_800));
        employeeMap.put("Yuki Tsunoda", new Employee("Yuki", "Tsunoda", 2, 41_500));

        System.out.println(findEmployee("Anna", "Svetova").hashCode());
    }

    @Override
    public Map<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {

        if (employeeMap.size() == maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        Employee newcomer = new Employee(nameHandler(firstName), nameHandler(lastName),
                department, salary);

        if (employeeMap.containsKey(newcomer.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(newcomer.getFullName(), newcomer);
        return newcomer;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = nameHandler(firstName) + " " + nameHandler(lastName);

        if (!employeeMap.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);

        if (employeeMap.containsKey(key)) {
            return employeeMap.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> showArray() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }

    private String getKey(String firstName, String lastName) {
        return nameHandler(firstName) + " " + nameHandler(lastName);
    }
}

