package tvn.springcourse.sensorApi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tvn.springcourse.sensorApi.model.Sensor;
import tvn.springcourse.sensorApi.service.SensorService;

@AllArgsConstructor
@RestController
@RequestMapping("/sensor")
public class SensorsController {

    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<Sensor> registerSensor(@RequestBody @Valid Sensor sensor, BindingResult bindingResult){
        //TODO validation, replace by dto
        sensorService.registerSensor(sensor);
        return new ResponseEntity<>(sensor,HttpStatus.OK);
    }

}
