package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class ServiceDTO {
    @Schema(description = "ID сервісу")
    private UUID id;

    @Schema(description = "Назва сервісу")
    private String name;

    @Schema(description = "Ціна сервісу")
    private Double cost;
}