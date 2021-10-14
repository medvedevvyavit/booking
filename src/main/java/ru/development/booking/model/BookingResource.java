package ru.development.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "booking_resource")
public class BookingResource implements Serializable {

    @Id
    private String id;

    @OneToOne(mappedBy = "resource", cascade = CascadeType.PERSIST)
    private Reservation reservation;
    private String name;
}
