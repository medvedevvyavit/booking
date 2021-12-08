package ru.development.booking.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import ru.development.booking.dto.ReservationFilterDto;
import ru.development.booking.repository.ReservationRepository;
import ru.development.booking.service.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DeleteReservationTasklet implements Tasklet {

    private final BookingService bookingService;
    private final ReservationRepository reservationRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        bookingService.searchReservations(new ReservationFilterDto(
                LocalDateTime.of(1900, 1, 1, 0, 0),
                LocalDate.now().atStartOfDay().minusDays(1)
        )).forEach(reservation -> reservationRepository.deleteById(reservation.getId()));

        return RepeatStatus.FINISHED;
    }
}
