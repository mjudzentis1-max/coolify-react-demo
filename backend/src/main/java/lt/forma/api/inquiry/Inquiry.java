package lt.forma.api.inquiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "inquiries")
class Inquiry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 120) private String name;
    @Column(nullable = false, length = 254) private String email;
    @Column(nullable = false, columnDefinition = "TEXT") private String message;
    @Column(name = "created_at", nullable = false) private Instant createdAt;

    protected Inquiry() { }
    Inquiry(String name, String email, String message, Instant createdAt) {
        this.name = name; this.email = email; this.message = message; this.createdAt = createdAt;
    }
    Long getId() { return id; }
    String getName() { return name; }
    String getEmail() { return email; }
    String getMessage() { return message; }
    Instant getCreatedAt() { return createdAt; }
}
