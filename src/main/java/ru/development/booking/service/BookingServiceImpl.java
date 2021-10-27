package ru.development.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.development.booking.dto.BookingResourceDto;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.dto.ReservationFilterDto;
import ru.development.booking.exception.EntityAlreadyExistsException;
import ru.development.booking.exception.ReservationCreatingException;
import ru.development.booking.mapper.BookingMapper;
import ru.development.booking.model.Reservation;
import ru.development.booking.model.ReservationId;
import ru.development.booking.repository.BookingResourceRepository;
import ru.development.booking.repository.ReservationRepository;
import ru.development.booking.repository.ReservationSpecification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static ru.development.booking.exception.ExceptionConstants.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingResourceRepository bookingResourceRepository;
    private final ReservationRepository reservationRepository;
    private final BookingMapper mapper;

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
    public List<ReservationDto> searchReservations(ReservationFilterDto filter) {
        return reservationRepository.findAll(new ReservationSpecification(filter))
                .stream()
                .map(mapper::toReservationDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationId saveReservation(ReservationDto reservationDto) {
        if (reservationRepository.existsById(reservationDto.getId())){
            throw new EntityAlreadyExistsException(format(ENTITY_ALREADY_EXISTS_MSG, "Reservation"));
        }
        validateByPeriod(reservationDto);

        return reservationRepository.save(mapper.toReservation(reservationDto)).getId();
    }

    private void validateByPeriod(ReservationDto reservationDto) {
        ReservationId id = reservationDto.getId();
        if (reservationRepository.findIdByCrossingPeriod(id.getId(), reservationDto.getStartDate(), reservationDto.getEndDate()) == null) {
            throw new EntityAlreadyExistsException(format(ENTITY_ALREADY_EXISTS_CROSSING_DATE_MSG, "Reservation"));
        }
        if (reservationDto.getStartDate().isBefore(LocalDateTime.now()) || reservationDto.getEndDate().isBefore(LocalDateTime.now())){
            throw new ReservationCreatingException(CREATE_RESERVATION_IN_THE_PAST);
        }
    }

    @Override
    public ReservationDto updateReservation(ReservationDto reservationForUpdate) {
        Reservation updatingReservation = reservationRepository.getById(reservationForUpdate.getId());

        validateByPeriod(reservationForUpdate);
        BeanUtils.copyProperties(reservationForUpdate, updatingReservation);

        return mapper.toReservationDto(reservationRepository.save(updatingReservation));
    }

    @Override
    public String saveResource(BookingResourceDto resourceDto) {
        String id = resourceDto.getId();
        if (bookingResourceRepository.existsById(id)){
            throw new EntityAlreadyExistsException(format(ENTITY_ALREADY_EXISTS_MSG, "BookingResource"));
        }
        return bookingResourceRepository.save(mapper.toBookingResource(resourceDto)).getId();
    }
}
