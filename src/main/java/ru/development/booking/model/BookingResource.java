package ru.development.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "booking_resource")
public class BookingResource implements Serializable {

    @Id
    private String id;

    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    private String name;
}
