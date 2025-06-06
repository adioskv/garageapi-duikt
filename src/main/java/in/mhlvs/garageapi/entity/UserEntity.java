package in.mhlvs.garageapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Schema(description = "Користувач системи")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID користувача")
    private UUID id;

    @Schema(description = "Логін", example = "stivstiverson")
    private String username;

    @Schema(description = "Роль", example = "USER")
    private String role;

    @Schema(description = "Повне імя користувача", example = "Ivan Ivanov")
    private String fullName;

    @Schema(description = "Email", example = "ivan@example.com")
    private String email;

    @Schema(description = "Пароль", example = "secure1234")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @Schema(description = "Список машин користувача")
    private List<CarEntity> cars;
}
