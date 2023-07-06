package tvn.springcourse.sensorApi.util;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorUtil {
    public static String getErrorMessage(Errors errors){
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError error: fieldErrors) {
            stringBuilder.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
        }
        return stringBuilder.toString();
    }
}
