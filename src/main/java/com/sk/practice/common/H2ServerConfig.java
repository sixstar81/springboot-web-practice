package com.sk.practice.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class H2ServerConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void initDirectory() throws IOException {
        Path directory = Paths.get("./data");
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
    }
}
