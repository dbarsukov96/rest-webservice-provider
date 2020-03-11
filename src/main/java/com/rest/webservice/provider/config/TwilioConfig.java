package com.rest.webservice.provider.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TwilioConfig {
    @Value("${twilio.account.sid}")
    private String accountSID;
    @Value("${twilio.auth.token}")
    private String authToken;
    @Value("${twilio.trial.number}")
    private String trialNumber;
}
