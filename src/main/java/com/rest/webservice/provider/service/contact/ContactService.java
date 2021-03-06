package com.rest.webservice.provider.service.contact;

import com.rest.webservice.provider.domain.Contact;

import java.util.List;

public interface ContactService {
    Contact saveContact(Contact contact);

    List<Contact> retrieveAllContacts();

    Contact getContactById(Long id);

    List<Contact> getContactsByName(String name);

    Contact getContactByPhoneNumber(String phoneNumber);

    Contact updateContact(Contact contact);

    void deleteContact(Long id);
}
