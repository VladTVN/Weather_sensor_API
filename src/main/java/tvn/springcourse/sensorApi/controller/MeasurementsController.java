package tvn.springcourse.sensorApi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tvn.springcourse.sensorApi.service.MeasurementService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/measurement")
public class MeasurementsController {
    private final MeasurementService measurementService;

    @GetMapping
    public ResponseEntity<Integer> getRainyDaysCount(){
        //TODO return DTO or map
        int rainyDaysCount = measurementService.getRainyDaysCount();
        return new ResponseEntity<>(rainyDaysCount, HttpStatus.OK);
    }

}
