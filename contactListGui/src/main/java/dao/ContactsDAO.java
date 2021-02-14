package dao;

import entity.Contact;
import java.util.List;

/**
 * Interface determines operations on contacts
 */
public interface ContactsDAO
{
    public Long addContact(Contact contact);

    public void updateContact(Contact contact);

    public void deleteContact(Long contactId);

    public Contact getContact(Long contactId);

    public List<Contact> findContacts();

    public void sortContactsList(boolean sort);
    
}
