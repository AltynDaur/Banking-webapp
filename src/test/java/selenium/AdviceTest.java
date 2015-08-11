package selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import selenium.WebDriverFactory;

/**
 * Created by Dauren_Altynbekov on 8/11/2015.
 */
public class AdviceTest {
    @Test
    public void testNextAdviceButton(){
        String currentUrl = WebDriverFactory.getWebDriverInstance().getCurrentUrl();
        WebDriverFactory.getWebDriverInstance().findElement(By.id("next")).click();
        Assert.assertNotEquals(WebDriverFactory.getWebDriverInstance().getCurrentUrl(),currentUrl);
    }
}
