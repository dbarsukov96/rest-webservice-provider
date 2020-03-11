package com.rest.webservice.provider.service.twilio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SmsRequest {
    private final String phoneNumber;
    private final String message;
}
