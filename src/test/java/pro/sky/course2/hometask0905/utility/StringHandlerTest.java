package pro.sky.course2.hometask0905.utility;

import org.junit.jupiter.api.Test;
import pro.sky.course2.hometask0905.exceptions.NotValidInputException;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.course2.hometask0905.utility.StringHandler.nameHandler;

class StringHandlerTest {

    @Test
    void nameHandler_shouldThrowWhenEmptyStringIsGiven() {
        String emptyStr = "";

        assertThrows(NotValidInputException.class, () -> nameHandler(emptyStr));
    }

    @Test
    void nameHandler_shouldThrowWhenStringIsNotAlpha() {
        String str = "ivan123";

        assertThrows(NotValidInputException.class, () -> nameHandler(str));
    }

    @Test
    void nameHandler_shouldCapitalizeWhenStringIsInLowerCase() {
        String actual = "ivan";
        String expected = "Ivan";

        assertEquals(expected, nameHandler(actual));
    }
}
