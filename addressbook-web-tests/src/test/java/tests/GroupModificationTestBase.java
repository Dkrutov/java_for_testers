package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTestBase extends TestBase {

    @Test
    void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
