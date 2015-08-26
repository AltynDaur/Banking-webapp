package junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.JUnit4;

/**
 * Created by Dauren_Altynbekov on 8/11/2015.
 */
public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DBTestSuite.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}