package ru.development.booking.config.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.job.Processor;
import ru.development.booking.job.Reader;
import ru.development.booking.job.Writer;
import ru.development.booking.model.Reservation;
import ru.development.booking.service.BookingService;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BookingService bookingService;

    @Bean
    public Job processJobinator(){
        return jobBuilderFactory.get("processJobinator")
                .flow(steporator())
                .end()
                .build();
    }

    @Bean
    public Step steporator() {
        return stepBuilderFactory.get("steporator").<ReservationDto, ReservationDto> chunk(1)
                .reader(new Reader(bookingService))
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }
}
