package gui;

import entity.Contact;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
    Model/representation of a table
 */
public class ContactModel extends AbstractTableModel
{
    private static final String[] headers = {"ID", "First name", "Last name", "Email", "Phone"};

    private final List<Contact> contacts;

    public ContactModel(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Contact contact = contacts.get(row);
        // Returns a name of a corresponding column
        switch (col) {
            case 0:
                return contact.getContactId().toString();
            case 1:
                return contact.getFirstName();
            case 2:
                return contact.getLastName();
            case 3:
                return contact.getEmail();
            default:
                return contact.getPhone();
        }
    }
}
