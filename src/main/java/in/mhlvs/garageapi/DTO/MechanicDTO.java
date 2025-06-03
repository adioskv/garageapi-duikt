package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
public class MechanicDTO {
    @Schema(description = "ID механика", example = "UUID string", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;
    private String name;
    private String specialization;
}
