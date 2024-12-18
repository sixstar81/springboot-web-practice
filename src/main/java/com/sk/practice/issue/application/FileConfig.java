package com.sk.practice.issue.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "file.upload")
@Getter
@Setter
public class FileConfig {
    private String path;
}
