package pro.sky.course2.hometask0905.services;

import org.junit.jupiter.api.Test;
import pro.sky.course2.hometask0905.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.hometask0905.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hometask0905.exceptions.EmployeeStorageIsFullException;
import pro.sky.course2.hometask0905.model.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.course2.hometask0905.constants.UsefulConstants.*;

public class EmployeeServiceImplTest {

    private final EmployeeService out = new EmployeeServiceImpl();

    private void fillStorage(int times) {
        String firstName = "a";
        int dep = 1;

        for (int i = 0; i < times; i++) {
            out.addEmployee(firstName, "testovich", dep, SAL);
            dep++;
            firstName += "a";
        }
    }

    private void addOneEmployee() {
        out.addEmployee(NAME, LASTNAME, 8, SAL);
    }

    @Test
    public void addEmployee_shouldThrowWhenEmployeeAlreadyIn() {
        addOneEmployee();
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.addEmployee(NAME, LASTNAME, 1, SAL));
    }

    @Test
    public void addEmployee_shouldThrowWhenMaxSizeIsReached() {
        fillStorage(20);

        assertThrows(EmployeeStorageIsFullException.class,
                () -> out.addEmployee(NAME, LASTNAME, 8, SAL));
    }

    @Test
    public void addEmployee_shouldAddCorrectly() {
        Employee expected = new Employee(NAME, LASTNAME, 8, SAL);

        assertEquals(expected, out.addEmployee(NAME, LASTNAME, 8, SAL));
    }

    @Test
    public void removeEmployee_shouldThrowWhenThereIsNoSuchEmployee() {
        assertThrows(EmployeeNotFoundException.class,
                () -> out.removeEmployee(NAME, LASTNAME));
    }

    @Test
    public void removeEmployee_shouldWorkCorrectly() {
        addOneEmployee();
        out.removeEmployee(NAME, LASTNAME);

        assertThrows(EmployeeNotFoundException.class,
                () -> out.findEmployee(NAME, LASTNAME));
    }

    @Test
    public void findEmployee_shouldThrowWhenThereIsNoSuchEmployee() {
        assertThrows(EmployeeNotFoundException.class,
                () -> out.findEmployee(NAME, LASTNAME));
    }
}