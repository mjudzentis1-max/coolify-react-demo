package lt.forma.api.inquiry;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllByOrderByCreatedAtDesc();
}
