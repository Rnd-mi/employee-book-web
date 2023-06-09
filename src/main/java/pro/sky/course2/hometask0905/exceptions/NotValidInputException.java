package pro.sky.course2.hometask0905.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Invalid \"first name\" or (and) \"last name\" : size, inappropriate characters"
)
public class NotValidInputException extends RuntimeException {
    public NotValidInputException() {
    }
}
