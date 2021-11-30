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
                LocalDateTime.now().minusDays(1)))
                .stream()
                .peek(reservationDto -> reservationRepository.deleteById(reservationDto.getId()));

        return RepeatStatus.FINISHED;
    }
}
