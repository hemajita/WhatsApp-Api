package com.example.WhatsApp.Api.Repository;

import com.example.WhatsApp.Api.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository <Contact,Long>{
    List<Contact> findByUserId(String userId);
}
