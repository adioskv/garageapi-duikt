package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AppointmentDTO {
    @Schema(description = "ID візиту", accessMode = Schema.AccessMode.READ_ONLY)
    public UUID id;

    @Schema(description = "Час візиту")
    private LocalDateTime dateTime;

    @Schema(description = "Статус візиту")
    public String status;

    @Schema(description = "ID машини")
    public UUID carId;

    @Schema(description = "ID механіка")
    public UUID mechanicId;

    @Schema(description = "Список ID сервісів")
    public List<UUID> serviceIds;
}