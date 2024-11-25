package tests;

import common.CommonFunction;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactsInfoTests extends TestBase {

    @Test
    void testPhone() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.phone(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var address = app.contacts().getAddress(contact);
        var expected = contact.address();
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testEmail() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var email = app.contacts().getEmail(contact);
        var expected = Stream.of(contact.email(),contact.email2(),contact.email3() )
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, email);
    }

    @Test
    void testContactInfo() {
        if (app.contacts().getCountContacts() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName(CommonFunction.randomString(10))
                    .withLastName(CommonFunction.randomString(10))
                    .withAddress(CommonFunction.randomString(10))
                    .withPhone(CommonFunction.randomInt(9))
                    .withHome(CommonFunction.randomInt(9))
                    .withWork(CommonFunction.randomInt(9))
                    .withSecondary(CommonFunction.randomInt(10))
                    .withEmail(CommonFunction.randomString(10))
                    .withEmail2(CommonFunction.randomString(10))
                    .withEmail3(CommonFunction.randomString(10))
                    .withPhoto(CommonFunction.randomFile("src/test/resources/images"))
            );
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var email = app.contacts().getEmail(contact);
        var address = app.contacts().getAddress(contact);
        var phones = app.contacts().getPhones(contact);
        var ContactInfo = new ArrayList<>(List.of(phones,address,email));
        var expectedPhones = Stream.of(contact.home(), contact.phone(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedAddress = contact.address();
        var expectedEmail = Stream.of(contact.email(),contact.email2(),contact.email3() )
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedContactInfo = new ArrayList<>(List.of(expectedPhones,expectedAddress,expectedEmail));
        Assertions.assertEquals(expectedContactInfo, ContactInfo);
    }
}
