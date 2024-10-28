package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithEmptyName() {
    app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithName() {
        app.contacts().createContact(new ContactData().withFirstName("FirstName"));
    }

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData().contactValidation("FirstName","MiddleName","LastName","City N, N street, 99","email@email","89997776655"));
    }
}
