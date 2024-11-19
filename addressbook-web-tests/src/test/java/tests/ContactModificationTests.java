package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withLastName("modified LastName");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void ContactModificationAddInGroup() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData());
//        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        var contactNonGroup = app.contacts().getListContactNonGroup();
        if (contactNonGroup.isEmpty()) {
            app.hbm().createContact(new ContactData());
            contactNonGroup = app.contacts().getListContactNonGroup();
        }
        var rnd = new Random();
        var index = rnd.nextInt(contactNonGroup.size());
        app.contacts().modifyContactInGroup(contactNonGroup.get(index), group);

        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contactNonGroup.get(index));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }


    @Test
    public void ContactModificationRemoveFromGroup() {
    }
}
