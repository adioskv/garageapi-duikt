package in.mhlvs.garageapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
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

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public CarEntity getCar() { return car; }
    public void setCar(CarEntity car) { this.car = car; }

    public MechanicEntity getMechanic() { return mechanic; }
    public void setMechanic(MechanicEntity mechanic) { this.mechanic = mechanic; }

    public List<ServiceEntity> getServices() { return services; }
    public void setServices(List<ServiceEntity> services) { this.services = services; }
}