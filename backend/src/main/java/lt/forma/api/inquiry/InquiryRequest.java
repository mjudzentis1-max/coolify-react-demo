package lt.forma.api.inquiry;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record InquiryRequest(@NotBlank @Size(max = 120) String name,
                      @NotBlank @Email @Size(max = 254) String email,
                      @NotBlank @Size(max = 4_000) String message) { }
