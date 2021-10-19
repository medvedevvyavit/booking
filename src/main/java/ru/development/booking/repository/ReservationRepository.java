package ru.development.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.development.booking.model.Reservation;
import ru.development.booking.model.ReservationId;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId>, JpaSpecificationExecutor<Reservation> {

}
