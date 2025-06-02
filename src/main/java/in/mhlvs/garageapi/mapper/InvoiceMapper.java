package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.InvoiceDTO;
import in.mhlvs.garageapi.entity.InvoiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceEntity toEntity(InvoiceDTO dto);
    InvoiceDTO toDto(InvoiceEntity entity);
}