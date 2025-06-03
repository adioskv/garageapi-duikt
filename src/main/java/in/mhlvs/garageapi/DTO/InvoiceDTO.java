package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class InvoiceDTO {
    @Schema(description = "ID чеку")
    private UUID id;

    @Schema(description = "ID візиту")
    private UUID appointmentId;

    @Schema(description = "Ціна")
    private Double amount;

    @Schema(description = "Деталі")
    private String details;
}