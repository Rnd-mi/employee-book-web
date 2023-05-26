package pro.sky.course2.hometask0905.services;

import pro.sky.course2.hometask0905.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface DepartmentService {

    Employee getMinSalaryInDep(int department);

    Employee getMaxSalaryInDep(int department);

    List<Employee> printEmployeesFromDep(int department);

    Map<Integer, List<Employee>> printDepartments();

    int getSalaryExpensesForDep(int department);

    long getAverageSalaryInDep(int department);

    String indexSalariesInDep(int department, int percent);
}
