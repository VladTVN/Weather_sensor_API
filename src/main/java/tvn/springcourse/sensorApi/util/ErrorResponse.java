package tvn.springcourse.sensorApi.util;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private long timestamp;
}
