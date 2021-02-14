package entity;

import java.util.Comparator;

public class MyContactsComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }

}
