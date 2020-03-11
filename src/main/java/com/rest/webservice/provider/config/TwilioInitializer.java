package com.rest.webservice.provider.config;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TwilioInitializer {
    private TwilioConfig twilioConfig;

    public TwilioInitializer(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccountSID(), twilioConfig.getAuthToken());
        log.info("Twilio initialized with account SID {}", twilioConfig.getAccountSID());
    }
}
