package in.mhlvs.garageapi.DTO;

import lombok.Data;
import java.util.UUID;

@Data
public class ServiceDTO {
    private UUID id;
    private String name;
    private Double cost;
}