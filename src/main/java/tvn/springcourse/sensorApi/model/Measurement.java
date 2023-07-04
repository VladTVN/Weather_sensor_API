package tvn.springcourse.sensorApi.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "temperature_value")
    private float temperatureValue;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;
}
