package dao;

import entity.Contact;
import entity.MyContactsComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ContactsSimpleDAO implements ContactsDAO
{
    private final List<Contact> contacts = new ArrayList<>();

    // Adding several contact from the very start by default
    public ContactsSimpleDAO() {
        addContact(new Contact("Adam", "Smith", "321-297-6347", "adam.smith@gmail.com"));
        addContact(new Contact("Miranda", "Shaffer", "718-348-1856", "mirandaShaffer@yahoo.com"));
        addContact(new Contact("Alvaro", "Mcgee", "915-781-8894", "alvaro_mcgee@outlook.com"));
    }
    

    public Long addContact(Contact contact) {
        Long id = generateContactId();
        contact.setContactId(id);
        contacts.add(contact);
        return id;
    }

    @Override
    public void updateContact(Contact contact) {
        Contact oldContact = getContact(contact.getContactId());
        if(oldContact != null) {
            oldContact.setFirstName(contact.getFirstName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setPhone(contact.getPhone());
            oldContact.setEmail(contact.getEmail());
        }
    }

    @Override
    public void deleteContact(Long contactId) {
        for(Iterator<Contact> it = contacts.iterator(); it.hasNext();) {
            Contact cnt = it.next();
            if(cnt.getContactId().equals(contactId)) {
                it.remove();
            }
        }
    }

    @Override
    public Contact getContact(Long contactId) {
        for(Contact contact : contacts) {
            if(contact.getContactId().equals(contactId)) {
                return contact;
            }
        }
        return null;
    }
    
    @Override
    public List<Contact> findContacts() {
        return contacts;
    }

    //Generates random id based on current time & returns it
    private Long generateContactId() {
        Long contactId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(getContact(contactId) != null) {
            contactId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return contactId;
    }

    public void sortContactsList(boolean ASC) {
        if(ASC) {
            Collections.sort(contacts, new MyContactsComparator());
        }
        else {
            Collections.sort(contacts, new MyContactsComparator().reversed());
        }
    }

}
