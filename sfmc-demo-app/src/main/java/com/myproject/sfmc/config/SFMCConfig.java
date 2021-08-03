package com.myproject.sfmc.config;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapConnection;
import com.myproject.sfmc.SFMCClientConstants;
import com.myproject.sfmc.SFMCService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SFMCConfigProperties.class})
public class SFMCConfig {

    @Bean
    public SFMCService sfmcService(ETClient etClient) {
        return new SFMCService(etClient);
    }

    @Bean
    public ETClient etClient(SFMCConfigProperties sfmcConfigProperties) throws ETSdkException {
        ETConfiguration configuration = new ETConfiguration();
        configuration.set(SFMCClientConstants.CLIENT_ID, sfmcConfigProperties.getClientId());
        configuration.set(SFMCClientConstants.CLIENT_SECRET, sfmcConfigProperties.getClientSecret());
        configuration.set(SFMCClientConstants.USE_OAUTH2_AUTHENTICATION, String.valueOf(sfmcConfigProperties.isUseOAuth2Authentication()));
        configuration.set(SFMCClientConstants.AUTH_ENDPOINT, sfmcConfigProperties.getAuthEndpoint());
        configuration.set(SFMCClientConstants.SOAP_ENDPOINT, sfmcConfigProperties.getSoapEndpoint());
        configuration.set(SFMCClientConstants.REST_ENDPOINT, sfmcConfigProperties.getRestEndpoint());
        ETClient etClient = new ETClient(configuration);

        return etClient;
    }
}
