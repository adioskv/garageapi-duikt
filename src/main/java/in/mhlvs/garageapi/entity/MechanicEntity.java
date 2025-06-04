package in.mhlvs.garageapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Schema(description = "Механік автосервісу")
public class MechanicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID Механіка", example = "e6e8e9b1-60ff-4eb3-bd12-1b8ff1b63f72")
    private UUID id;

    @Schema(description = "Ім'я механіка", example = "Стів Стіверсон")
    private String name;

    @Schema(description = "Спеціалізація", example = "двигуни")
    private String specialization;

    @OneToMany(mappedBy = "mechanic", fetch = FetchType.EAGER)
    @Schema(description = "Назначення на роботу")
    @JsonManagedReference
    private List<AppointmentEntity> appointments;
}
