package pro.sky.course2.hometask0905.services;

import org.springframework.stereotype.Service;
import pro.sky.course2.hometask0905.model.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final Map<String, Employee> employeeMap;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        employeeMap = employeeServiceImpl.getEmployeeMap();
    }

    private Stream<Employee> getFilteredStream(int department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment() == department);
    }

    @Override
    public Employee getMinSalaryInDep(int department) {
        Optional<Employee> employee = employeeMap.values().stream()
                .min(Comparator.comparingInt(e -> e.getSalary()));

        return employee.orElseThrow();
    }

    @Override
    public Employee getMaxSalaryInDep(int department) {
        Optional<Employee> employee = employeeMap.values().stream()
                .max(Comparator.comparingInt(e -> e.getSalary()));

        return employee.orElseThrow();
    }

    @Override
    public List<Employee> printEmployeesFromDep(int department) {
        return getFilteredStream(department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> printDepartments() {
        Map<Integer, List<Employee>> result = new HashMap<>();

        result = employeeMap.values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));

        return result;
    }

    @Override
    public int getSalaryExpensesForDep(int department) {
        return getFilteredStream(department)
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    @Override
    public long getAverageSalaryInDep(int department) {
        long count = getFilteredStream(department).count();
        return getSalaryExpensesForDep(department) / count;
    }

    @Override
    public Stream<Employee> indexSalariesInDep(int department, int percent) {
        double index = (double) percent / 100;
        getFilteredStream(department)
                .forEach(e -> e.setSalary((int) Math.round(e.getSalary() + (e.getSalary() * index))));

        return getFilteredStream(department);
    }
}
