package com.droidwars.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;

@Configuration
@ConfigurationProperties(prefix = "droidwars.server")
@Data
public class ConfigProperties {

    private String version;
    private Game game;
    private FileStorage fileStorage;

    @Data
    public static class Game {

        @Min(1)
        @NotNull
        private Integer coreThreadCount;

        @Min(1)
        @NotNull
        private Integer maxThreadCount;

        @Min(0)
        @NotNull
        private Integer maxQueueSize;

        @Min(0)
        @NotNull
        private Integer awaitTerminationSeconds;

    }

    @Data
    public static class FileStorage {

        @NotNull
        private Path rootPath;
    }

}
