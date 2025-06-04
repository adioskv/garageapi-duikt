package in.mhlvs.garageapi.DTO;

import in.mhlvs.garageapi.entity.AppointmentEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MechanicDTO {
    @Schema(description = "ID механіка", accessMode = Schema.AccessMode.READ_ONLY)
    public UUID id;

    @Schema(description = "Ім'я механіка")
    public String name;

    @Schema(description = "Спеціалізація")
    public String specialization;

    @Schema(description = "Назначення на роботу", accessMode = Schema.AccessMode.READ_ONLY)
    private List<AppointmentEntity> appointments;
}