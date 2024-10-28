package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactRemovelTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().removeContact();
    }
}
