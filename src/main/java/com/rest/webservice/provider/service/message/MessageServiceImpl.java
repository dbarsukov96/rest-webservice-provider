package com.rest.webservice.provider.service.message;

import com.rest.webservice.provider.domain.Contact;
import com.rest.webservice.provider.domain.Message;
import com.rest.webservice.provider.service.twilio.SmsRequest;
import com.rest.webservice.provider.service.twilio.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private SmsSender smsSender;

    @Override
    public void sendMessage(Contact contact, Message message) {
        SmsRequest smsRequest = new SmsRequest(contact.getPhoneNumber(), message.getText());
        smsSender.sendSms(smsRequest);
    }
}
