package in.mhlvs.garageapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.mhlvs.garageapi.DTO.AppointmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GarageapiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AppointmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUnauthorizedAppointmentCreation() throws Exception {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setDateTime(LocalDateTime.now().plusDays(1));
        dto.setCarId(UUID.randomUUID());
        dto.setServiceIds(List.of(UUID.randomUUID(), UUID.randomUUID()));

        mockMvc.perform(post("/api/appointments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized());
    }

    // Додаткові позитивні тести з токеном можна реалізувати через @WithMockUser або JWT
}