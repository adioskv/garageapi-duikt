package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AppointmentDTO {
    @Schema(description = "ID візиту")
    private UUID id;

    @Schema(description = "Час візиту")
    private LocalDateTime dateTime;

    @Schema(description = "Статус візиту")
    private String status;

    @Schema(description = "ID машини")
    private UUID carId;

    @Schema(description = "ID механіка")
    private UUID mechanicId;

    @Schema(description = "Список ID сервісів")
    private List<UUID> serviceIds;
}