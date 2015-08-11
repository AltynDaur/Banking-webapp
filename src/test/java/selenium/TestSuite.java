package selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Dauren_Altynbekov on 8/11/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(AdviceTest.class)
public class TestSuite {
    @BeforeClass
    public static void startBrowser() {
        WebDriverFactory.getWebDriverInstance().get("http://fucking-great-advice.ru/advice");
    }

    @AfterClass
    public static void quitBrowser() {
        WebDriverFactory.getWebDriverInstance().close();
    }
}
