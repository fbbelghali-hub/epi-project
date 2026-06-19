package com.example.epi.entity;

import com.example.epi.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "epirequest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpiRequest extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Column(nullable = false)
    private Integer requestQuantity;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "epi_id", nullable = false)
    private Epi epi;

    @Column(nullable = false)
    private String size;

    private LocalDate purchaseDate;

    @PrePersist
    public void prePersist() {
        if (this.requestDate == null) {
            this.requestDate = LocalDate.now();
        }
        if (this.status == null) {
            this.status = RequestStatus.EN_COURS;
        }
    }
}
