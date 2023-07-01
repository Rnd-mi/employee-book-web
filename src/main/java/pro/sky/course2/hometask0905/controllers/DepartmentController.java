package pro.sky.course2.hometask0905.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.model.Employee;
import pro.sky.course2.hometask0905.services.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService depService;

    @Autowired
    public DepartmentController(DepartmentService depService) {
        this.depService = depService;
    }

    @GetMapping("/{id}/salary/min")
    public Employee findMinSalary(@PathVariable("id") int id) {
        return depService.getMinSalaryInDep(id);
    }

    @GetMapping("/{id}/salary/max")
    public Employee findMaxSalary(@PathVariable("id") int id) {
        return depService.getMaxSalaryInDep(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> printEmployeesFromDep(@PathVariable("id") int id) {
        List<Employee> employees = depService.printEmployeesFromDep(id);

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employees;
    }

    @GetMapping("/{id}/salary/sum")
    public String getSalaryExpensesForDep(@PathVariable("id") int id) {
        return "Salary expenses for " + id + " department : " + depService.getSalaryExpensesForDep(id);
    }

    @GetMapping("/{id}/average-salary")
    public String getAverageSalaryInDep(@PathVariable("id") int id) {
        return "Average salary in " + id + " department : " + depService.getAverageSalaryInDep(id);
    }

    @GetMapping("/{id}/index/{percent}")
    public Stream<Employee> indexSalariesInDep(@PathVariable("id") int id,
                                               @PathVariable("percent") int percent) {
        return depService.indexSalariesInDep(id, percent);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> printDepartments() {
        return depService.printDepartments();
    }
}
