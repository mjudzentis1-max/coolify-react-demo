package lt.forma.api.inquiry;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InquiryController.class)
@TestPropertySource(properties = "forma.api-key=test-key")
class InquiryControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private InquiryService inquiryService;

    @Test
    void savesInquiryWhenApiKeyIsValid() throws Exception {
        when(inquiryService.create(any())).thenReturn(
            new InquiryResponse(1L, "Aistė", "aiste@example.com", "Sveiki", Instant.parse("2026-01-01T00:00:00Z"))
        );

        mockMvc.perform(post("/api/inquiries")
                .header("X-Api-Key", "test-key")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Aistė\",\"email\":\"aiste@example.com\",\"message\":\"Sveiki\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("aiste@example.com"));
    }

    @Test
    void listsInquiriesOnlyWithApiKey() throws Exception {
        when(inquiryService.findAll()).thenReturn(List.of(
            new InquiryResponse(1L, "Aistė", "aiste@example.com", "Sveiki", Instant.parse("2026-01-01T00:00:00Z"))
        ));

        mockMvc.perform(get("/api/inquiries").header("X-Api-Key", "test-key"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Aistė"));
    }

    @Test
    void rejectsRequestsWithoutApiKey() throws Exception {
        mockMvc.perform(get("/api/inquiries"))
            .andExpect(status().isBadRequest());
    }
}
