package tvn.springcourse.sensorApi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tvn.springcourse.sensorApi.dto.MeasurementDTO;
import tvn.springcourse.sensorApi.dto.MeasurementResponse;
import tvn.springcourse.sensorApi.model.Measurement;
import tvn.springcourse.sensorApi.service.MeasurementService;
import tvn.springcourse.sensorApi.util.ErrorResponse;
import tvn.springcourse.sensorApi.util.ErrorUtil;
import tvn.springcourse.sensorApi.util.exception.MeasurementNotCreatedException;
import tvn.springcourse.sensorApi.util.MeasurementValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Map<String,Integer>> getRainyDaysCount(){
        Map<String, Integer> response = new HashMap<>();
        response.put("numberOfRainyDays", measurementService.getRainyDaysCount());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MeasurementDTO> registerMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                              BindingResult bindingResult) throws MeasurementNotCreatedException {

        Measurement measurement= convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);
        if (bindingResult.hasErrors()){
            throw new MeasurementNotCreatedException(ErrorUtil.getErrorMessage(bindingResult));
        }
        measurementService.registerMeasurement(measurement);
        return new ResponseEntity<>(measurementDTO, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<MeasurementResponse> getListMeasurements(){
        List<Measurement> measurementList = measurementService.gelListMeasurements();
        List<MeasurementDTO> measurementDTOList = measurementList.stream().map(this::convertToMeasurementDTO).toList();
        MeasurementResponse measurementResponse = new MeasurementResponse(measurementDTOList);
        return new ResponseEntity<>(measurementResponse, HttpStatus.OK);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleNotCreatedException(MeasurementNotCreatedException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
