package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 3 ; i++) {
            result.add(new ContactData(randomString(i * 10),randomString(i * 10),randomString(i * 10),randomString(i * 10),randomString(i * 10),randomString(i * 10)));
        }
        return result;
    }

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

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCountContacs();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCountContacs();
        Assertions.assertEquals(contactCount + 1,newContactCount);
    }
}
