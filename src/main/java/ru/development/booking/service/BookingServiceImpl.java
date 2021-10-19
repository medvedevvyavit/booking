package ru.development.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.development.booking.dto.BookingResourceDto;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.mapper.BookingMapper;
import ru.development.booking.model.ReservationId;
import ru.development.booking.repository.BookingResourceRepository;
import ru.development.booking.repository.ReservationRepository;
import ru.development.booking.repository.ReservationSpecification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingResourceRepository bookingResourceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookingMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public ReservationDto getReservationById(ReservationId id) {
        return mapper.toReservationDto(reservationRepository.getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResourceDto getResourceById(String id) {
        return mapper.toBookingResourceDto(bookingResourceRepository.getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResourceDto> getAllResources() {
        return bookingResourceRepository.findAll()
                .stream()
                .map(mapper::toBookingResourceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> searchReservations(LocalDateTime startDate, LocalDateTime endDate) {
        return reservationRepository.findAll(new ReservationSpecification(startDate, endDate))
                .stream()
                .map(mapper::toReservationDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationId saveReservation(ReservationDto reservation) {
        return reservationRepository.save(mapper.toReservation(reservation)).getId();
    }
}
