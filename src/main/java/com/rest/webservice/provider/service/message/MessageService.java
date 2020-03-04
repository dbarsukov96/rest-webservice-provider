package com.rest.webservice.provider.service.message;

import com.rest.webservice.provider.domain.Contact;
import com.rest.webservice.provider.domain.Message;

public interface MessageService {
    void sendMessage(Contact contact, Message message);
}
