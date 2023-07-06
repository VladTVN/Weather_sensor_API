package tvn.springcourse.sensorApi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tvn.springcourse.sensorApi.dto.SensorDTO;
import tvn.springcourse.sensorApi.model.Sensor;
import tvn.springcourse.sensorApi.service.SensorService;
import tvn.springcourse.sensorApi.util.ErrorResponse;
import tvn.springcourse.sensorApi.util.ErrorUtil;
import tvn.springcourse.sensorApi.util.exception.SensorNotCreatedException;
import tvn.springcourse.sensorApi.util.SensorValidator;

@AllArgsConstructor
@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @PostMapping("/registration")
    public ResponseEntity<SensorDTO> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) throws SensorNotCreatedException {
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new SensorNotCreatedException(ErrorUtil.getErrorMessage(bindingResult));
        }

        sensorService.registerSensor(sensor);
        return new ResponseEntity<>(sensorDTO, HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleSensorNotCreatedException(SensorNotCreatedException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.OK);
    }


}
