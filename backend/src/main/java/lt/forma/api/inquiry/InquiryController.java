package lt.forma.api.inquiry;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {
    private final InquiryService inquiryService;
    private final String apiKey;

    public InquiryController(InquiryService inquiryService, @Value("${forma.api-key}") String apiKey) {
        this.inquiryService = inquiryService;
        this.apiKey = apiKey;
    }

    @PostMapping
    public ResponseEntity<InquiryResponse> create(@RequestHeader("X-Api-Key") String requestKey,
                                                   @Valid @RequestBody InquiryRequest request) {
        authorize(requestKey);
        InquiryResponse inquiry = inquiryService.create(request);
        return ResponseEntity.created(URI.create("/api/inquiries/" + inquiry.id())).body(inquiry);
    }

    @GetMapping
    public List<InquiryResponse> findAll(@RequestHeader("X-Api-Key") String requestKey) {
        authorize(requestKey);
        return inquiryService.findAll();
    }

    private void authorize(String requestKey) {
        if (!apiKey.equals(requestKey)) throw new ResponseStatusException(UNAUTHORIZED);
    }
}
