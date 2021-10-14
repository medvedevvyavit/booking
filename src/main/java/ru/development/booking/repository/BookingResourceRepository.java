package ru.development.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.development.booking.model.BookingResource;

public interface BookingResourceRepository extends JpaRepository<BookingResource, String> {

}
