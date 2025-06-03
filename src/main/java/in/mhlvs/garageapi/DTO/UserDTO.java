package in.mhlvs.garageapi.DTO;

import in.mhlvs.garageapi.entity.CarEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {
    @Schema(description = "ID користувача")
    public UUID id;

    @Schema(description = "Ім'я користувача")
    public String username;

    @Schema(description = "Роль")
    public String role;

    @Schema(description = "Повне ім'я")
    public String fullName;

    @Schema(description = "Email")
    public String email;

    @Schema(description = "Пароль")
    public String password;

    @Schema(description = "Машини")
    private List<CarEntity> cars;
}