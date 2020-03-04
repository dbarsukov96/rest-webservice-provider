package com.rest.webservice.provider.service.message;

import com.rest.webservice.provider.domain.Contact;
import com.rest.webservice.provider.domain.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public void sendMessage(Contact contact, Message message) {
        // TODO: Sending SMS using a third-party mobile operator service...
    }
}
