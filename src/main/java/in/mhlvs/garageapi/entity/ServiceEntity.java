package in.mhlvs.garageapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Schema(description = "ID сервісу")
    private UUID id;

    @Schema(description = "Ім'я механіка", example = "Євлампій")
    private String name;

    @Schema(description = "Ціна послуги", example = "233.33")
    private Double cost;
}