package in.mhlvs.garageapi.DTO;

import lombok.Data;
import java.util.UUID;

@Data
public class MechanicDTO {
    private UUID id;
    private String name;
    private String specialization;
}
