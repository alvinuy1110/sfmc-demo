package com.myproject.sfmc;

import com.myproject.sfmc.config.HttpClientConfig;
import com.myproject.sfmc.config.SFMCConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(
        classes = {SFMCServiceTest.TestConfig.class, SFMCConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {}
//properties = {"spring.profiles.active:testHttp"}
)

@TestPropertySource("classpath:application-test.properties")
@Slf4j
public class SFMCServiceTest extends AbstractTestNGSpringContextTests  {

    @Autowired
    private SFMCService sfmcService;

    @Test
    @SneakyThrows
    public void testGetFolders() {
        sfmcService.getFolders();
    }


    @Test
    @SneakyThrows
    public void testGetTracking() {
        sfmcService.getTracking();
    }


    @Configuration
    @EnableConfigurationProperties
    @Import({HttpClientConfig.class})
    public static class TestConfig {

        static {
            System.setProperty("javax.net.ssl.trustStore","/home/user/git/sfmc-demo/certs/sfmctrust");
            System.setProperty("javax.net.ssl.trustStorePassword","changeit");
        }
    }
}
