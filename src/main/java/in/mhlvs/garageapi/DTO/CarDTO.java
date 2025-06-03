package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CarDTO {
    @Schema(description = "ID автомобіля")
    public UUID id;

    @Schema(description = "Номерний знак")
    private String licensePlate;

    @Schema(description = "Модель автомобіля")
    public String model;

    @Schema(description = "ID власника")
    public UUID ownerId;
}