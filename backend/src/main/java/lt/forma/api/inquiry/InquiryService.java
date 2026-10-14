package lt.forma.api.inquiry;

import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class InquiryService {
    private final InquiryRepository inquiryRepository;
    InquiryService(InquiryRepository inquiryRepository) { this.inquiryRepository = inquiryRepository; }
    InquiryResponse create(InquiryRequest request) {
        return InquiryResponse.from(inquiryRepository.save(new Inquiry(request.name(), request.email(), request.message(), Instant.now())));
    }
    List<InquiryResponse> findAll() {
        return inquiryRepository.findAllByOrderByCreatedAtDesc().stream().map(InquiryResponse::from).toList();
    }
}
