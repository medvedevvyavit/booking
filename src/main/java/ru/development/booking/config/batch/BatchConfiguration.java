package ru.development.booking.config.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.development.booking.job.Processor;
import ru.development.booking.job.Reader;
import ru.development.booking.job.Writer;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJobinator(){
        return jobBuilderFactory.get("processJobinator")
                .flow(steporator())
                .end()
                .build();
    }

    @Bean
    public Step steporator() {
        return stepBuilderFactory.get("steporator").<String, String> chunk(1)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }
}
