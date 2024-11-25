package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void testContactsInfo() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var email = app.contacts().getEmail(contact);
        var address = app.contacts().getAddress(contact);
        var phones = app.contacts().getPhones(contact);
        var expectedPhones = Stream.of(contact.home(), contact.phone(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedAddress = contact.address();
        var expectedEmail = Stream.of(contact.email(),contact.email2(),contact.email3() )
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedEmail, email);
        Assertions.assertEquals(expectedAddress, address);
        Assertions.assertEquals(expectedPhones, phones);
    }
}
