package pro.sky.course2.hometask0905.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Stream<Employee> getFilteredStream(int department) {
        return employeeService.getEmployeeMap().values().stream()
                .filter(e -> e.getDepartment() == department);
    }

    @Override
    public Employee getMinSalaryInDep(int department) {
        Optional<Employee> employee = getFilteredStream(department)
                .min(Comparator.comparingInt(e -> e.getSalary()));


        return employee.orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Employee getMaxSalaryInDep(int department) {
        Optional<Employee> employee = getFilteredStream(department)
                .max(Comparator.comparingInt(e -> e.getSalary()));

        return employee.orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public List<Employee> printEmployeesFromDep(int department) {
        return getFilteredStream(department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> printDepartments() {
        Map<Integer, List<Employee>> result;

        result = employeeService.getEmployeeMap().values().stream()
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
