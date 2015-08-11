package selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import selenium.WebDriverFactory;

/**
 * Created by Dauren_Altynbekov on 8/11/2015.
 */
public class BankingAppLoginTest {
    @Test
    public void testBankingLoginAsAdmin(){
        WebDriverFactory.getWebDriverInstance().findElement(By.name("firstName")).sendKeys("Dauren");
        WebDriverFactory.getWebDriverInstance().findElement(By.name("password")).sendKeys("111");
        WebDriverFactory.getWebDriverInstance().findElement(By.id("loginBtn")).click();
        Assert.assertTrue(WebDriverFactory.getWebDriverInstance().getTitle().contains("Thanks for visiting"));
    }
}
