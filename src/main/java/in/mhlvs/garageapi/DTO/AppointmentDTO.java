package in.mhlvs.garageapi.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AppointmentDTO {
    private UUID id;
    private LocalDateTime dateTime;
    private String status;
    public UUID carId;
    public UUID mechanicId;
    public List<UUID> serviceIds;
}