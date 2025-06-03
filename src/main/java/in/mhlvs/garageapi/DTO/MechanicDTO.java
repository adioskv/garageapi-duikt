package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class MechanicDTO {
    @Schema(description = "ID механіка")
    private UUID id;

    @Schema(description = "Ім'я механіка")
    private String name;

    @Schema(description = "Спеціалізація")
    private String specialization;
}