package tvn.springcourse.sensorApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tvn.springcourse.sensorApi.model.Measurement;
import tvn.springcourse.sensorApi.repository.MeasurementRepository;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Transactional
    public void registerMeasurement(Measurement measurement){
        measurementRepository.save(measurement);
    }

    public List<Measurement> gelAllMeasurements(){
        return measurementRepository.findAll();
    }

    public int getRainyDaysCount(){
        return measurementRepository.countByRainingTrue();
    }
}
