package tvn.springcourse.sensorApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tvn.springcourse.sensorApi.model.Sensor;
import tvn.springcourse.sensorApi.repository.SensorRepository;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Transactional
    public void registerSensor(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
