package ru.development.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.development.booking.model.Reservation;
import ru.development.booking.model.ReservationId;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId>, JpaSpecificationExecutor<Reservation> {
    @Query(value = "select id from Reservation where id.id = :id and startDate <= :end_date and endDate >= :start_date")
    ReservationId findIdByCrossingPeriod(@Param("id") String id, @Param("start_date") LocalDateTime startDate, @Param("end_date") LocalDateTime endDate);
}
