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
@Schema(description = "Механик автосервиса")
public class MechanicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Уникальный идентификатор механика", example = "e6e8e9b1-60ff-4eb3-bd12-1b8ff1b63f72")
    private UUID id;

    @Schema(description = "Имя механика", example = "Иван Петров")
    private String name;

    @Schema(description = "Специализация", example = "двигатели")
    private String specialization;

    @OneToMany(mappedBy = "mechanic")
    @Schema(description = "Назначения на работу")
    private List<AppointmentEntity> appointments;
}
