package org.Alex.service;

import org.Alex.domain.Contact;
import org.Alex.persistence.ContactRepository;
import org.Alex.transfer.SaveContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ContactService {

    private static ContactRepository contactRepository = new ContactRepository();

    public static void createContact(SaveContactRequest request) throws SQLException, IOException, ClassNotFoundException {

        System.out.println("Creating contact:" + request);
        contactRepository.createContact();

    }
    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retriving contacts...");
        return contactRepository.getContacts();
    }

    public void deleteContact(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting contact:" + id);
        contactRepository.deleteContact();
    }
}
