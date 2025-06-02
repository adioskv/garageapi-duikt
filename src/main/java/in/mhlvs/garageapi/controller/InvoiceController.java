package in.mhlvs.garageapi.controller;

import in.mhlvs.garageapi.DTO.InvoiceDTO;
import in.mhlvs.garageapi.entity.InvoiceEntity;
import in.mhlvs.garageapi.mapper.InvoiceMapper;
import in.mhlvs.garageapi.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @PostMapping("/create")
    public ResponseEntity<InvoiceDTO> create(@RequestBody InvoiceDTO dto) {
        InvoiceEntity entity = invoiceMapper.toEntity(dto);
        return ResponseEntity.ok(invoiceMapper.toDto(invoiceRepository.save(entity)));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<InvoiceDTO> get(@PathVariable UUID id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<InvoiceDTO> getAll() {
        return StreamSupport.stream(invoiceRepository.findAll().spliterator(), false)
                .map(invoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InvoiceDTO> update(@PathVariable UUID id, @RequestBody InvoiceDTO dto) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    invoice.setTotalAmount(dto.getAmount());
                    invoice.setDetails(dto.getDetails());
                    return ResponseEntity.ok(invoiceMapper.toDto(invoiceRepository.save(invoice)));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}