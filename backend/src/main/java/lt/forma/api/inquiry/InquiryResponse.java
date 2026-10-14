package lt.forma.api.inquiry;

import java.time.Instant;


record InquiryResponse(Long id, String name, String email, String message, Instant createdAt) {
    static InquiryResponse from(Inquiry inquiry) {
        return new InquiryResponse(inquiry.getId(), inquiry.getName(), inquiry.getEmail(), inquiry.getMessage(), inquiry.getCreatedAt());
    }
}
