package in.mhlvs.garageapi.service;

import in.mhlvs.garageapi.DTO.AppointmentDTO;
import in.mhlvs.garageapi.entity.*;
import in.mhlvs.garageapi.mapper.AppointmentMapper;
import in.mhlvs.garageapi.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CarRepository carRepository;
    private final MechanicRepository mechanicRepository;
    private final ServiceRepository serviceRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              CarRepository carRepository,
                              MechanicRepository mechanicRepository,
                              ServiceRepository serviceRepository,
                              AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.carRepository = carRepository;
        this.mechanicRepository = mechanicRepository;
        this.serviceRepository = serviceRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public AppointmentDTO create(AppointmentDTO dto) {
        AppointmentEntity entity = appointmentMapper.toEntity(dto);
        entity.setStatus("заплановано");
        entity.setCar(carRepository.findById(dto.carId).orElseThrow());
        entity.setMechanic(mechanicRepository.findById(dto.mechanicId).orElseThrow());
        entity.setServices(dto.serviceIds.stream().map(id -> serviceRepository.findById(id).orElseThrow()).collect(Collectors.toList()));
        return appointmentMapper.toDto(appointmentRepository.save(entity));
    }

    public List<AppointmentDTO> getByMechanicForToday(UUID mechanicId) {
        LocalDate today = LocalDate.now();
        return StreamSupport.stream(appointmentRepository.findAll().spliterator(), false)
                .filter(a -> a.getMechanic().getId().equals(mechanicId))
                .filter(a -> a.getDateTime().toLocalDate().equals(today))
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public AppointmentDTO complete(UUID id) {
        AppointmentEntity a = appointmentRepository.findById(id).orElseThrow();
        a.setStatus("виконано");
        return appointmentMapper.toDto(appointmentRepository.save(a));
    }
}