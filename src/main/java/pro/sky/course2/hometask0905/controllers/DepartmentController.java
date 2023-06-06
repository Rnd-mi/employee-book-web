package pro.sky.course2.hometask0905.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.course2.hometask0905.model.Employee;
import pro.sky.course2.hometask0905.services.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/dep")
public class DepartmentController {
    private final DepartmentService depService;

    @Autowired
    public DepartmentController(DepartmentService depService) {
        this.depService = depService;
    }

    @GetMapping("/min/{dep}")
    public Employee findMinSalary(@PathVariable("dep") int dep) {
        return depService.getMinSalaryInDep(dep);
    }

    @GetMapping("/max/{dep}")
    public Employee findMaxSalary(@PathVariable("dep") int dep) {
        return depService.getMaxSalaryInDep(dep);
    }

    @GetMapping("/{dep}")
    public List<Employee> printEmployeesFromDep(@PathVariable("dep") int dep) {
        return depService.printEmployeesFromDep(dep);
    }

    @GetMapping("/expenses/{dep}")
    public String getSalaryExpensesForDep(@PathVariable("dep") int dep) {
        return "Salary expenses for " + dep + " department : " + depService.getSalaryExpensesForDep(dep);
    }

    @GetMapping("/average-salary/{dep}")
    public String getAverageSalaryInDep(@PathVariable("dep") int dep) {
        return "Average salary in " + dep + " department : " + depService.getAverageSalaryInDep(dep);
    }

    @GetMapping("/index/{dep}/{percent}")
    public Stream<Employee> indexSalariesInDep(@PathVariable("dep") int dep,
                                               @PathVariable("percent") int percent) {
        return depService.indexSalariesInDep(dep, percent);
    }

    @GetMapping("/list")
    public Map<Integer, List<Employee>> printDepartments() {
        return depService.printDepartments();
    }
}
