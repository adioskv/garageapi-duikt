package in.mhlvs.garageapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    @Schema(description = "ID пользователя", example = "UUID string")
    private UUID id;

    @Schema(description = "Имя пользователя", example = "Ivan Ivanov")
    private String fullName;

    @Schema(description = "Email", example = "ivan@example.com")
    private String email;

    @Schema(description = "Пароль", example = "secure1234")
    private String password;
}
