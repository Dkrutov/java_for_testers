package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        /*for (var firstName : List.of("", "firstName")) {
            for (var lastName : List.of("", "lastName")) {
                for (var address : List.of("", "address")) {
                    for (var email : List.of("", "email@email")) {
                        for (var phone : List.of("", "99999999999")) {
                            result.add(new ContactData()
                                    .withFirstName(firstName)
                                    .withLastName(lastName)
                                    .withMiddleName("middleName")
                                    .withAddress(address)
                                    .withEmail(email)
                                    .withPhone(phone));
                        }
                    }
                }
            }
        }*/
        for (int i = 0; i < 3; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withMiddleName(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withEmail(randomString(i * 10))
                    .withPhone(randomInt(11)));
        }
        return result;
    }

    public static List<ContactData> contactNegativeProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "firstName'", "", "", "", "", "")));
        return result;
    }

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData().contactValidation("FirstName", "MiddleName", "LastName", "City N, N street, 99", "email@email", "89997776655"));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contacts().getListContact();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getListContact();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts
                .size() - 1).id())
                .withMiddleName("")
                .withAddress("")
                .withEmail("")
                .withPhone(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts,expectedList);
    }

    @ParameterizedTest
    @MethodSource("contactNegativeProvider")
    public void canNotCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contacts().getListContact();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getListContact();
        Assertions.assertEquals(oldContacts, newContacts);
    }
}
