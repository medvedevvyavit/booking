package ru.development.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @EmbeddedId
    private ReservationId id;

    @OneToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private BookingResource resource;
    private String creatorName;
    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(name = "person_num")
    private Integer personNumber;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
