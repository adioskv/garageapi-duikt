package in.mhlvs.garageapi.DTO;

import in.mhlvs.garageapi.entity.CarEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {
    @Schema(description = "ID користувача", accessMode = Schema.AccessMode.READ_ONLY)
    public UUID id;

    @Schema(description = "Ім'я користувача")
    public String username;

    @Schema(description = "Роль", accessMode = Schema.AccessMode.READ_ONLY)
    public String role;

    @Schema(description = "Повне ім'я")
    public String fullName;

    @Schema(description = "Email")
    public String email;

    @Schema(description = "Пароль")
    public String password;

    @Schema(description = "Машини юзера", accessMode = Schema.AccessMode.READ_ONLY)
    private List<UUID> carIds;
}