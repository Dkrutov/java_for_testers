package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        click(By.linkText("add new"));
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        returnToHomePage();
        selectContact();
        removeSelectedContact();
        returnToHomePage();
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("mobile"), contact.phone());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public boolean isContactPresent() {
        returnToHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }


}
