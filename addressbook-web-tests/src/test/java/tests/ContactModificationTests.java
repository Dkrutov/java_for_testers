package tests;

import common.CommonFunction;
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

//        if (contactNonGroup.isEmpty()) {
            var contactAddInGroup = new ContactData()
                    .withFirstName(CommonFunction.randomString(10))
                    .withLastName(CommonFunction.randomString(10));
            app.contacts().createContact(contactAddInGroup);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        var contactNonGroup = app.contacts().getListContactNonGroup();
//        var contactNonGroup = app.contacts().getListContactNonGroup();
//        }
//        var rnd = new Random();
//        var index = rnd.nextInt(contactNonGroup.size());
        contactNonGroup.sort(compareById);
        app.contacts().modifyContactInGroup(contactNonGroup.get(contactNonGroup.size()-1), group);
        var newRelated = app.hbm().getContactsInGroup(group);

        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contactNonGroup.get(contactNonGroup.size()-1));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }


    @Test
    public void ContactModificationRemoveFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var rnd = new Random();
        var oldGroups = app.hbm().getGroupList();
        var index = rnd.nextInt(oldGroups.size());
        var group = app.hbm().getGroupList().get(index);
        var oldRelated = app.hbm().getContactsInGroup(group);
        if (oldRelated.isEmpty()) {
            app.hbm().createContact(new ContactData());
            var contactNonGroup = app.contacts().getListContactNonGroup();
            app.contacts().modifyContactInGroup(contactNonGroup.get(0), group);
            oldRelated = app.hbm().getContactsInGroup(group);
        }
        var contact = oldRelated.get(0);
        app.contacts().modifyContactOutGroup(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(contact);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }
}
