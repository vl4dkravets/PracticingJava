package dao;

/**
 * The place to determine which implementation of data acccess model will be used
 * In our case, we have a single implementation - ContactsSimpleDAO
 */
public class ContactsDAOFactory
{
    public static ContactsDAO getContactDAO() {
        return new ContactsSimpleDAO();
    }
}
