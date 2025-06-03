package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    @Schema(description = "ID користувача")
    private UUID id;

    @Schema(description = "Ім'я користувача")
    private String username;

    @Schema(description = "Роль")
    private String role;

    @Schema(description = "Повне ім'я")
    private String fullName;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Пароль")
    private String password;
}