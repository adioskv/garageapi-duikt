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
@Schema(description = "Invoice юзера")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID чеку", example = "e6e8e9b1-60ff-4eb3-bd12-1b8ff1b63f72")
    private UUID id;

    @OneToOne
    @Schema(description = "Візит")
    private AppointmentEntity appointment;

    @Schema(description = "Ціна", example = "1499.22")
    private Double totalAmount;

    @Schema(description = "Деталі", example = "Було замінено масло, ...")
    private String details;
}
