package com.rest.webservice.provider.service.twilio;

import com.rest.webservice.provider.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TwilioSmsSender implements SmsSender {
    @Autowired
    private TwilioConfig twilioConfig;

    @Override
    public void sendSms(SmsRequest smsRequest) {
        PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
        String body = smsRequest.getMessage();

        MessageCreator creator = Message.creator(to, from, body);
        creator.create();
        log.info("Sending sms: {} to the number {}", body, to);
    }
}
