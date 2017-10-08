package com.droidwars.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private ConfigProperties configProperties;
    @Autowired
    private TaskExecutor taskExecutor;

    @Bean
    @Lazy
    public ThreadPoolTaskExecutor taskExecutor() {
        log.debug("Creating taskExecutor bean");

        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(configProperties.getGame().getCoreThreadCount());
        pool.setMaxPoolSize(configProperties.getGame().getMaxThreadCount());
        pool.setQueueCapacity(configProperties.getGame().getMaxQueueSize());

        pool.setAwaitTerminationSeconds(configProperties.getGame().getAwaitTerminationSeconds());
        pool.setWaitForTasksToCompleteOnShutdown(false);

        return pool;
    }

    @Scheduled(fixedDelay = 10_000)
    public void checkNewBattles() {
        log.debug("Start check new battles task");

        taskExecutor.execute(() -> {
            log.info("Task executed");
        });

    }

}
