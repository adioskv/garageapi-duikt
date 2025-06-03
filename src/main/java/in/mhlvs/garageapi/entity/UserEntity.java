package in.mhlvs.garageapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Schema(description = "Пользователь системы")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID пользователя")
    private UUID id;

    @Schema(description = "Имя пользователя (логин)", example = "ivanivanov")
    private String username;

    @Schema(description = "Роль пользователя", example = "USER")
    private String role;

    @Schema(description = "Полное имя пользователя", example = "Ivan Ivanov")
    private String fullName;

    @Schema(description = "Email", example = "ivan@example.com")
    private String email;

    @Schema(description = "Пароль", example = "secure1234")
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<CarEntity> cars;
}
