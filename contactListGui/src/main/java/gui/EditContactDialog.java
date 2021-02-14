package gui;

import entity.Contact;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class EditContactDialog extends JDialog implements ActionListener
{
    // Button names
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Fields to manually establish the layout

    // Indent
    private static final int PAD = 10;
    // width of a textfield's name
    private static final int W_L = 100;
    //width of a textfield's
    private static final int W_T = 300;
    // width of a button
    private static final int W_B = 120;
    // height of a button
    private static final int H_B = 25;

    // textfields
    private final JTextPane txtFirstName = new JTextPane();
    private final JTextPane txtLastName = new JTextPane();
    private final JTextPane txtPhone = new JTextPane();
    private final JTextPane txtEmail = new JTextPane();

    // holds contacts id
    private Long contactId = null;
    // stores the state of a button SAVE
    private boolean save = false;

    public EditContactDialog() {
        this(null);
    }

    public EditContactDialog(Contact contact) {
        // Removes standard layout - we'll set up our own
        setLayout(null);

        // Set's up positions & names of textfields
        buildFields();
        // Initializes textfield's forms
        initFields(contact);
        // Builds buttons
        buildButtons();

        // turns on modal mode - means, dialog window in the only active windows, can wok only here
        setModal(true);
        setResizable(false);
        setBounds(300, 300, 450, 200);
        setVisible(true);
    }

    // Размещаем метки и поля ввода на форме
    private void buildFields() {
        JLabel lblFirstName = new JLabel("First name:");
        lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        // coordinates for text field's name
        lblFirstName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        add(lblFirstName);
        // coordinates for the text field
        txtFirstName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        txtFirstName.setBorder(BorderFactory.createEtchedBorder());
        add(txtFirstName);

        // Setting for last name text field
        JLabel lblLastName = new JLabel("Last name:");
        lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLastName.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblLastName);
        txtLastName.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtLastName.setBorder(BorderFactory.createEtchedBorder());
        add(txtLastName);

        // Setting for phone# text field
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblPhone);
        txtPhone.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtPhone.setBorder(BorderFactory.createEtchedBorder());
        add(txtPhone);

        // Setting for email text field
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblEmail);
        txtEmail.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtEmail.setBorder(BorderFactory.createEtchedBorder());
        add(txtEmail);
    }

    // Initializes text fields using instance of a passed Contact object
    private void initFields(Contact contact) {
        if (contact != null) {
            contactId = contact.getContactId();
            txtFirstName.setText(contact.getFirstName());
            txtLastName.setText(contact.getLastName());
            txtEmail.setText(contact.getEmail());
            txtPhone.setText(contact.getPhone());
        }
    }

    // Positions buttons on the main panel
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    @Override
    // Handles presses of buttons
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // stores the state of SAVE button
        save = SAVE.equals(action);
        // closes the dialog window
        setVisible(false);
    }

    // returns the state - whether to save the data
    public boolean isSave() {
        return save;
    }

    // Create Contact object based on entered input
    public Contact getContact() {
        Contact contact = new Contact(contactId, txtFirstName.getText(),
                txtLastName.getText(), txtPhone.getText(), txtEmail.getText());
        return contact;
    }
}
