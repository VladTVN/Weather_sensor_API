package tvn.springcourse.sensorApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tvn.springcourse.sensorApi.model.Measurement;
import tvn.springcourse.sensorApi.model.Sensor;
import tvn.springcourse.sensorApi.repository.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Transactional
    public void registerMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement){
        Sensor searchSensor = measurement.getSensor();
        Sensor sensorFromDB = sensorService.findByName(searchSensor.getName()).get();
        measurement.setSensor(sensorFromDB);
        measurement.setRegistrationDate(LocalDateTime.now());
    }

    public List<Measurement> gelListMeasurements(){
        return measurementRepository.findAll();
    }

    public int getRainyDaysCount(){
        return measurementRepository.countByRainingTrue();
    }
}
