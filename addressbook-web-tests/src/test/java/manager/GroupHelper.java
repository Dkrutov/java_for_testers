package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;



    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void removeGroup() {
        openGroupPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupPage();
    }

    public GroupHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
    }



    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }



    private void removeSelectedGroup() {
        click(By.name("delete"));
    }




    private void selectGroup() {
        click(By.name("selected[]"));
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }



    private void returnToGroupPage() {
        click(By.linkText("group page"));
    }
    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }
}
