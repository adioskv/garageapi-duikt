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
@Schema(description = "Автомобиль клиента")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Уникальный идентификатор автомобиля", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
    private UUID id;

    @Schema(description = "Госномер", example = "AB1234CD")
    private String licensePlate;

    @Schema(description = "Модель автомобиля", example = "Toyota Corolla")
    private String model;

    @ManyToOne
    @Schema(description = "Владелец автомобиля")
    private UserEntity owner;

    @OneToMany(mappedBy = "car")
    @Schema(description = "Список записей на обслуживание")
    private List<AppointmentEntity> appointments;
}