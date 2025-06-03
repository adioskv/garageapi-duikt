package in.mhlvs.garageapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Schema(description = "Авто клієнта")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID автомобіля", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
    private UUID id;

    @Schema(description = "Номерний знак", example = "AB1234CD")
    private String licensePlate;

    @Schema(description = "Модель автомобіля", example = "Toyota Corolla")
    private String model;

    @ManyToOne
    @Schema(description = "Власник автомобіля")
    private UserEntity owner;

    @OneToMany(mappedBy = "car")
    @Schema(description = "Список записів на обслуговування")
    private List<AppointmentEntity> appointments;
}