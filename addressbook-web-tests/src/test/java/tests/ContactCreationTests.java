package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunction;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstName : List.of("", "firstName")) {
//            for (var lastName : List.of("", "lastName")) {
//                for (var address : List.of("", "address")) {
//                    for (var email : List.of("", "email@email")) {
//                        for (var phone : List.of("", "99999999999")) {
//                            result.add(new ContactData()
//                                    .withFirstName(firstName)
//                                    .withLastName(lastName)
//                                    .withMiddleName("middleName")
//                                    .withAddress(address)
//                                    .withEmail(email)
//                                    .withPhone(phone)
//                                    .withPhoto(CommonFunction.randomFile("src/test/resources/images")));
//                        }
//                    }
//                }
//            }
//        }
        var mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<ContactData> contactNegativeProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "firstName'", "", "", "", "", "", CommonFunction.randomFile("src/test/resources/images"))));
        return result;
    }

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData()
                .withFirstName(CommonFunction.randomString(10))
                .withLastName(CommonFunction.randomString(10))
                .withMiddleName(CommonFunction.randomString(10))
                .withAddress(CommonFunction.randomString(10))
                .withEmail(CommonFunction.randomString(10))
                .withPhone(CommonFunction.randomInt(11))
                .withPhoto(CommonFunction.randomFile("src/test/resources/images")));
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
                .withPhone("")
                .withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
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
