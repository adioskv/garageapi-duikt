package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class CarDTO {
    @Schema(description = "Уникальный идентификатор автомобиля", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Госномер", example = "AB1234CD")
    private String licensePlate;

    @Schema(description = "Модель автомобиля", example = "Toyota Corolla")
    private String model;

    @Schema(description = "ID владельца", example = "UUID", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID ownerId;
}
