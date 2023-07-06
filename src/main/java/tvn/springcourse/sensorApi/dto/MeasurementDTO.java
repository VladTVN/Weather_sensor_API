package tvn.springcourse.sensorApi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeasurementDTO {
    @NotNull
    private Boolean raining;

    @NotNull
    @Min(value = -100)
    @Max(value = 100)
    private Float value;

    @NotNull(message = "Sensor cant be empty")
    private SensorDTO sensor;
}
