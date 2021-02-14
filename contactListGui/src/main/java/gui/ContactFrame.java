package gui;

import business_logic.ContactsManager;
import entity.Contact;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ContactFrame extends JFrame implements ActionListener
{
    private static final String LOAD = "Load";
    private static final String ADD = "Add";
    private static final String EDIT = "Edit";
    private static final String DELETE = "Delete";
    private static final String SORT = "Sort";

    //Configurations for sorting
    private final String sortingAsc = " (ASC)";
    private final String sortingDes = " (DESC)";
    private boolean sorting = true;

    private final ContactsManager contactManager = new ContactsManager();
    private final JTable contactTable = new JTable();
    private final JButton sortButton;

    // We create all for the client's frame here - everything what will be displayed to a client
    public ContactFrame() {
        // Selecting a property in our table - only one row can be selected
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Single table element takes up all the available space
        gbc.fill = GridBagConstraints.BOTH;
        // Settings up padding for what's inside a table's element
        gbc.insets = new Insets(5, 5, 0, 5);

        // Panel with buttons
        JPanel btnPanel = new JPanel();

        btnPanel.setLayout(gridbag);

        // Creating buttons & connecting time to an event listener
        btnPanel.add(createButton(gridbag, gbc, LOAD, LOAD));
        btnPanel.add(createButton(gridbag, gbc, ADD, ADD));
        btnPanel.add(createButton(gridbag, gbc, EDIT, EDIT));
        btnPanel.add(createButton(gridbag, gbc, DELETE, DELETE));

        sortButton = createButton(gridbag, gbc, SORT+sortingAsc, SORT);
        btnPanel.add(sortButton);

        // Panel dedicated to hold buttons - takes up a specific part of a windows
        JPanel left = new JPanel();

        left.setLayout(new BorderLayout());

        left.add(btnPanel, BorderLayout.SOUTH);

        add(left, BorderLayout.EAST);

        // Adding a table to the main display
        add(new JScrollPane(contactTable), BorderLayout.CENTER);


        setBounds(100, 200, 900, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Loading/updating list of contacts to the display's table
        loadContact();
    }

    // Method creates buttons while setting yp their properties, layout and an event it'd produce
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String buttonName, String command) {

        JButton button = new JButton(buttonName);

        button.setActionCommand(command);

        button.addActionListener(this);
        // Constraints which determines button's position on the screen
        gridbag.setConstraints(button, gbc);

        return button;
    }

    @Override
    // Handling of press of a button
    public void actionPerformed(ActionEvent ae) {

        String action = ae.getActionCommand();

        switch (action) {
            case LOAD:
                loadContact();
                break;
            case ADD:
                addContact();
                break;
            case EDIT:
                editContact();
                break;
            case DELETE:
                deleteContact();
                break;
            case SORT:
                sortContacts();
                break;
        }
    }

    private void loadContact() {
        // Get the most recent, up-to-date list with contacts
        List<Contact> contacts = contactManager.findContacts();
        // Create a model for representing list of contacts
        ContactModel cm = new ContactModel(contacts);
        // Passing the model to the display's table
        contactTable.setModel(cm);
    }

    private void addContact() {
        // Creating a dialog for entering data
        EditContactDialog ecd = new EditContactDialog();
        // passing the instance which will handle the input
        saveContact(ecd);
    }

    private void editContact() {
        // gets the selected row
        int sr = contactTable.getSelectedRow();
        // if we have the actual row selected => then,
        if (sr != -1) {
            // getting id of a contact which was selected
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            // finding & getting contact itself
            Contact cnt = contactManager.getContact(id);
            // Creating a dialog windows & passing the contact there
            // so field will be pre-initialized in dialog's text fields
            EditContactDialog ecd = new EditContactDialog(contactManager.getContact(id));
            // reloading contact's list to get fresh data being used
            saveContact(ecd);
        } else {
            // error dialog
            JOptionPane.showMessageDialog(this, "You should choose a row before making changes");
        }
    }

    private void deleteContact() {
        int sr = contactTable.getSelectedRow();
        if (sr != -1) {
            // getting selected contact's id
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            // deleting a contact
            contactManager.deleteContact(id);
            // updating list of contacts
            loadContact();
        } else {
            JOptionPane.showMessageDialog(this, "You should choose a row before making changes");
        }
    }

    private void saveContact(EditContactDialog ecd) {
        // if you pressed save => then,
        if (ecd.isSave()) {
            // first, getting entered data in the dialog window
            Contact cnt = ecd.getContact();
            if (cnt.getContactId() != null) {
                // if there was ID match, then the operating is update
                contactManager.updateContact(cnt);
            } else {
                // Otherwise, we're adding a new contact
                contactManager.addContact(cnt);
            }
            // in the end, refresh the list & at the same time the table
            loadContact();
        }
    }

    private void sortContacts() {
        contactManager.sortContactsList(sorting);
        loadContact();

        // Reverse the ordering option
        sorting = !sorting;
        updateSortButton();
    }

    private void updateSortButton() {
        if(sorting) {
            sortButton.setText(SORT+sortingAsc);
        }
        else {
            sortButton.setText(SORT+sortingDes);
        }
    }
}
