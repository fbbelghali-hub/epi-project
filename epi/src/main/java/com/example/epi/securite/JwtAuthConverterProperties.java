package com.example.epi.securite;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.*;
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class JwtAuthConverterProperties {

    private String resourceId;

}
