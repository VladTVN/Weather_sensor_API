package tvn.springcourse.sensorApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MeasurementResponse {
    private List<MeasurementDTO> measurementDTOList;
}
