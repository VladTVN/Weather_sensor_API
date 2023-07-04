package tvn.springcourse.sensorApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "Sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name cant be empty")
    @NotBlank(message = "Name cant be empty")
    @Length(min = 3, max = 30, message = "Name length should be between 3 and 30 characters")
    private String name;

}
