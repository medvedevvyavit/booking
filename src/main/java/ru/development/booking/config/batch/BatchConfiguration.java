package ru.development.booking.config.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.development.booking.job.DeleteReservationTasklet;
import ru.development.booking.service.BookingService;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DeleteReservationTasklet deleteReservationTasklet;

    @Bean
    public Job processJobinator(){
        return jobBuilderFactory.get("processJobinator")
                .start(steporator())
                .build();
    }

    @Bean
    public Step steporator() {
        return stepBuilderFactory.get("steporator").tasklet(deleteReservationTasklet).build();
    }
}
