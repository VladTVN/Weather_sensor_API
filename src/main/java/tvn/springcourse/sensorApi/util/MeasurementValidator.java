package tvn.springcourse.sensorApi.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tvn.springcourse.sensorApi.model.Measurement;
import tvn.springcourse.sensorApi.model.Sensor;
import tvn.springcourse.sensorApi.service.SensorService;

import java.util.Optional;

@AllArgsConstructor
@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurementFotValidation = (Measurement) target;
        if (measurementFotValidation.getSensor() == null){
            return;
        }
        Optional<Sensor> optionalSensor = sensorService.findByName(measurementFotValidation.getSensor().getName());
        if (optionalSensor.isEmpty()){
            errors.rejectValue("sensor","", "sensor with this name does not exist");
        }
    }
}
