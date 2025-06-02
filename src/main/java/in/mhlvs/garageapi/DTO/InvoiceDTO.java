package in.mhlvs.garageapi.DTO;

import lombok.Data;
import java.util.UUID;

@Data
public class InvoiceDTO {
    private UUID id;
    private Double amount;
    private String details;
}