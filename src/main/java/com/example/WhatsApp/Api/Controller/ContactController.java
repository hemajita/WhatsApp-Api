package com.example.WhatsApp.Api.Controller;

import com.example.WhatsApp.Api.Model.Contact;
import com.example.WhatsApp.Api.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
        if (contact.getUserId() == null || contact.getContactId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Contact contact1 = contactService.saveContact(contact);

        return ResponseEntity.ok(contact1);
    }
    @GetMapping
    public ResponseEntity<List<Contact>> getContacts(@RequestParam("userId") String userId) {
        List<Contact> contacts = contactService.getContacts(userId);
        return ResponseEntity.ok(contacts);
    }
}
