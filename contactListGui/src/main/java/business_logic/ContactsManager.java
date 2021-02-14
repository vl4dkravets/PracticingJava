package business_logic;

import dao.ContactsDAO;

import dao.ContactsDAOFactory;
import entity.Contact;
import java.util.List;

/**
 * Class which provides realization of operations with contacts
 */
public class ContactsManager
{
    private final ContactsDAO dao;
    
    public ContactsManager() {
        dao = ContactsDAOFactory.getContactDAO();
    }

    public Long addContact(Contact contact) {
        return dao.addContact(contact);
    }

    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }

    public void deleteContact(Long contactId) {
        dao.deleteContact(contactId);
    }


    public Contact getContact(Long contactId) {
        return dao.getContact(contactId);
    }

    public List<Contact> findContacts() {
        return dao.findContacts();
    }

    public void sortContactsList(boolean sort) { dao.sortContactsList(sort);}
}
