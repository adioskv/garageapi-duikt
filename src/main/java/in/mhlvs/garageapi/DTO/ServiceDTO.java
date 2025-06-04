package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class ServiceDTO {
    @Schema(description = "ID сервісу", accessMode = Schema.AccessMode.READ_ONLY)
    public UUID id;

    @Schema(description = "Назва сервісу")
    public String name;

    @Schema(description = "Ціна сервісу")
    public Double cost;
}