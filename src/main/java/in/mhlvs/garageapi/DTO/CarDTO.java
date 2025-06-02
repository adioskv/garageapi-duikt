package in.mhlvs.garageapi.DTO;

import lombok.Data;
import java.util.UUID;

@Data
public class CarDTO {
    private UUID id;
    private String licensePlate;
    private String model;
}