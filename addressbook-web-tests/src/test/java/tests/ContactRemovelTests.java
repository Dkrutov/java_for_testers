package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovelTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData());
        }
        int contactCount = app.contacts().getCountContacts();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCountContacts();
        Assertions.assertEquals(contactCount - 1,newContactCount);
    }

    @Test
    public void canRemoveAllContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getCountContacts());
    }
}
