package tvn.springcourse.sensorApi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "The \"rain\" flag cannot be empty")
    private boolean raining;

    @Column(name = "temperature_value")
    @NotEmpty
    @Min(value = -100)
    @Max(value = 100)
    private float temperatureValue;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotEmpty(message = "Sensor cant be empty")
    private Sensor sensor;
}
