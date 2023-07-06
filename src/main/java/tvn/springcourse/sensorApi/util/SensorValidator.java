package tvn.springcourse.sensorApi.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tvn.springcourse.sensorApi.model.Sensor;
import tvn.springcourse.sensorApi.service.SensorService;

import java.util.Optional;

@AllArgsConstructor
@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensorForValidation = (Sensor) target;
        Optional<Sensor> optionalSensor = sensorService.findByName(sensorForValidation.getName());
        if (optionalSensor.isPresent()){
            errors.rejectValue("name", "", "Sensor with this name already exist");
        }
    }
}
