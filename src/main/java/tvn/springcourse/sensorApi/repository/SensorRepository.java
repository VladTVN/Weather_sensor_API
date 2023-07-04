package tvn.springcourse.sensorApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tvn.springcourse.sensorApi.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
}
