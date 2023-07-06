package tvn.springcourse.sensorApi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SensorDTO {
    @NotBlank(message = "Name cant be empty")
    @Length(min = 3, max = 30, message = "Name length should be between 3 and 30 characters")
    private String name;
}
