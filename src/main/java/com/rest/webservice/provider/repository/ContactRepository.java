package com.rest.webservice.provider.repository;

import com.rest.webservice.provider.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByName(String name);

    Contact findByPhoneNumber(String phoneNumber);
}
