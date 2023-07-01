package pro.sky.course2.hometask0905.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.model.Employee;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pro.sky.course2.hometask0905.constants.UsefulConstants.*;

public class DepartmentServiceImplTest {

    private EmployeeService employeeService;

    private DepartmentService out;

    @BeforeEach
    public void setup() {
        employeeService = mock(EmployeeService.class);
        out = new DepartmentServiceImpl(employeeService);
    }


    @Test
    public void getMinSalaryInDep_shouldReturnMinSalary() {
        when(employeeService.getEmployeeMap()).thenReturn(getMap());

        assertEquals(MIN_SAL, out.getMinSalaryInDep(DEP).getSalary());
    }

    @Test
    public void getMaxSalaryInDep_shouldReturnMaxSalary() {
        when(employeeService.getEmployeeMap()).thenReturn(getMap());

        assertEquals(MAX_SAL, out.getMaxSalaryInDep(DEP).getSalary());
    }

    @Test
    public void getSalaryMethods_shouldThrowIfThereIsNoEmployeesInDep() {
        assertThrows(EmployeeNotFoundException.class,
                () -> out.getMinSalaryInDep(1));

        assertThrows(EmployeeNotFoundException.class,
                () -> out.getMaxSalaryInDep(1));
    }

    @Test
    public void printEmployeesFromDep_shouldReturnEmptyList() {
        assertTrue(out.printEmployeesFromDep(1).isEmpty());
    }

    @Test
    public void printEmployeesFromDep_shouldReturnCorrectly() {
        when(employeeService.getEmployeeMap()).thenReturn(getMap());

        assertEquals(3, out.printEmployeesFromDep(1).size());
    }

    @Test
    public void getSalaryExpensesForDep_shouldReturnZero() {
        assertEquals(0, out.getSalaryExpensesForDep(1));
    }

    @Test
    public void getSalaryExpensesForDep_shouldReturnSumOfSalaries() {
        when(employeeService.getEmployeeMap()).thenReturn(getMap());
        int expected = SAL + MIN_SAL + MAX_SAL;

        assertEquals(expected, out.getSalaryExpensesForDep(1));
    }

    @Test
    public void getAverageSalaryInDep_shouldReturnAverageSalary() {
        when(employeeService.getEmployeeMap()).thenReturn(getMap());
        int expected = (SAL + MIN_SAL + MAX_SAL) / 3;

        assertEquals(expected, out.getAverageSalaryInDep(1));
    }

    @Test
    public void indexSalariesInDep_shouldNotChangeSalariesIfIndexIsZero() {
        when(employeeService.getEmployeeMap()).thenReturn(getMap());
        int expected = out.getSalaryExpensesForDep(1);
        int actual = out.indexSalariesInDep(1, 0)
                .mapToInt(e -> e.getSalary()).sum();

        assertEquals(expected, actual);
    }

    @Test
    public void indexSalariesInDep_shouldChangeSalariesIfIndexIsNotZero() {
        when(employeeService.getEmployeeMap()).thenReturn(getMap());
        int expected = out.getSalaryExpensesForDep(1);
        int actual = out.indexSalariesInDep(1, 60)
                .mapToInt(e -> e.getSalary()).sum();

        assertNotEquals(expected, actual);
    }

    private Map<String, Employee> getMap() {
        return new HashMap<>(Map.of
                (NAME + " " + LASTNAME, new Employee(NAME, LASTNAME, DEP, SAL),
                 NAME + " Andreev", new Employee("Alex", LASTNAME, DEP, MAX_SAL),
                 "Alex " + LASTNAME, new Employee(NAME, "Andreev", DEP, MIN_SAL)));
    }

}