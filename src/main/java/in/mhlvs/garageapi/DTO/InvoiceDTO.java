package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class InvoiceDTO {
    @Schema(description = "ID чеку")
    public UUID id;

    @Schema(description = "ID візиту")
    public UUID appointmentId;

    @Schema(description = "Ціна")
    public Double amount;

    @Schema(description = "Деталі")
    public String details;
}