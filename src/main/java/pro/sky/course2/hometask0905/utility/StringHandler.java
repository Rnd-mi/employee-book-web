package pro.sky.course2.hometask0905.utility;

import pro.sky.course2.hometask0905.exceptions.NotValidInputException;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

public class StringHandler {

    public static String nameHandler(String string) {
        if (!isAlpha(string)) {
            throw new NotValidInputException();
        }

        return capitalize(string);
    }
}
