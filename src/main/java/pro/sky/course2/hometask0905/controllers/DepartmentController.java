package pro.sky.course2.hometask0905.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.hometask0905.model.Employee;
import pro.sky.course2.hometask0905.services.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dep")
public class DepartmentController {
    private final DepartmentService depService;

    @Autowired
    public DepartmentController(DepartmentService depService) {
        this.depService = depService;
    }

    @GetMapping("/min")
    public Employee findMinSalary(@RequestParam int dep) {
        return depService.getMinSalaryInDep(dep);
    }

    @GetMapping("/max")
    public Employee findMaxSalary(@RequestParam int dep) {
        return depService.getMaxSalaryInDep(dep);
    }

    @GetMapping()
    public List<Employee> printEmployeesFromDep(@RequestParam int dep) {
        return depService.printEmployeesFromDep(dep);
    }

    @GetMapping("/expenses")
    public String getSalaryExpensesForDep(@RequestParam int dep) {
        return "Salary expenses for " + dep + " department : " + depService.getSalaryExpensesForDep(dep);
    }

    @GetMapping("/average-salary")
    public String getAverageSalaryInDep(@RequestParam int dep) {
        return "Average salary in " + dep + " department : " + depService.getAverageSalaryInDep(dep);
    }

    @GetMapping("/index")
    public String indexSalariesInDep(@RequestParam int dep, int percent) {
        return depService.indexSalariesInDep(dep, percent);
    }

    @GetMapping("/list")
    public Map<Integer, List<Employee>> printDepartments() {
        return depService.printDepartments();
    }
}
