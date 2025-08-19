package com.example.board.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix="file")
public class StorageProperties {

	private String uploadDir;
}
