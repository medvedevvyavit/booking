package ru.development.booking.job;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobTrigger {

    private final JobLauncher jobLauncher;
    private final Job processJob;

    @SneakyThrows
    @Scheduled(cron = "${booking.job.cron}")
    public void invokeJob() {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(processJob, jobParameters);
        log.info("Batch job has been invoked");
    }
}
