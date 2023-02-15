package cucumber.tasks.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class Utility {

    public static void moveToElement(String xpathValue) {
        Actions action = new Actions(getDriver());
        WebElement we = getDriver().findElement(By.xpath(xpathValue));
        action.moveToElement(we).build().perform();
    }
}
