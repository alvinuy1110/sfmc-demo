package com.myproject.sfmc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "sfmc.config")
@Data
@Validated
public class SFMCConfigProperties {

    @NotBlank
    private String clientId;
    @NotBlank
    private String clientSecret;
    @NotBlank
    private String authEndpoint;
    @NotBlank
    private String soapEndpoint;

    @NotBlank
    private String restEndpoint;
    private  boolean useOAuth2Authentication;
}
