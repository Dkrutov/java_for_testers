package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "firstName")) {
            for (var lastName : List.of("", "lastName")) {
                for (var address : List.of("", "lastName")) {
                    for (var email : List.of("", "email@email")) {
                        for (var phone : List.of("", "99999999999")) {
                            result.add(new ContactData(firstName, "middleName", lastName, address, email, phone));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            result.add(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomInt(11)));
        }
        return result;
    }

    public static List<ContactData> contactNegativeProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("firstName'", "", "", "", "", "")));
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
        app.contacts().createContact(new ContactData().contactValidation("FirstName", "MiddleName", "LastName", "City N, N street, 99", "email@email", "89997776655"));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCountContacts();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCountContacts();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @ParameterizedTest
    @MethodSource("contactNegativeProvider")
    public void canNotCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCountContacts();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCountContacts();
        Assertions.assertEquals(contactCount, newContactCount);
    }
}
