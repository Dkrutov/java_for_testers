import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupPage();
        if (!isElementPresent(By.name("selected[]"))) {
            createGroup("group name", "group header", "group footer");
        }
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }


}
