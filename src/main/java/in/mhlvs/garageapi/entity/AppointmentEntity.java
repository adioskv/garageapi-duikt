package in.mhlvs.garageapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime dateTime;

    private String status;

    @ManyToOne
    private CarEntity car;

    @ManyToOne
    private MechanicEntity mechanic;

    @ManyToMany
    private List<ServiceEntity> services;
}
