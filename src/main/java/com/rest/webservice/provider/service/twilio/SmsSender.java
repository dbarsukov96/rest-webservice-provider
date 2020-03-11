package com.rest.webservice.provider.service.twilio;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
