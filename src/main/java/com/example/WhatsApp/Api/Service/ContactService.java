package com.example.WhatsApp.Api.Service;

import com.example.WhatsApp.Api.Model.Contact;
import com.example.WhatsApp.Api.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact){
Contact contact1=new Contact();
contact1.setContactId(contact.getContactId());
contact1.setUserId(contact.getUserId());
        return contactRepository.save(contact1);
    }
    public List<Contact> getContacts(String userId) {

        return contactRepository.findByUserId(userId);
    }
}
