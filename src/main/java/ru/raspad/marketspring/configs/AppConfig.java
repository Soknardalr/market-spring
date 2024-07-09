package ru.raspad.marketspring.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "secret.properties")
public class AppConfig {
}
