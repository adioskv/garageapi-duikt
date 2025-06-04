package in.mhlvs.garageapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private CarEntity car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private MechanicEntity mechanic;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ServiceEntity> services;
}
