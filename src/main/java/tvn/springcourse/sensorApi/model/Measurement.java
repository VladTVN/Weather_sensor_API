package tvn.springcourse.sensorApi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @Column(name = "temperature_value")
    @NotNull
    @Min(value = -100)
    @Max(value = 100)
    private Float value;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "Sensor cant be empty")
    private Sensor sensor;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
}
