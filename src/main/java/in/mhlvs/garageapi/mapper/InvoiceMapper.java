package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.InvoiceDTO;
import in.mhlvs.garageapi.entity.InvoiceEntity;
import in.mhlvs.garageapi.resolver.EntityResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = EntityResolver.class)
public interface InvoiceMapper {
    @Mappings({
            @Mapping(source = "appointmentId", target = "appointment", qualifiedByName = "resolveAppointment"),
            @Mapping(source = "amount", target = "totalAmount")
    })
    InvoiceEntity toEntity(InvoiceDTO dto);

    @Mappings({
            @Mapping(source = "appointment.id", target = "appointmentId"),
            @Mapping(source = "totalAmount", target = "amount")
    })
    InvoiceDTO toDto(InvoiceEntity entity);
}
