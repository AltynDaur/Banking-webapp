package junit;

import com.epam.javalab.webapp.account.Account;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Dauren_Altynbekov on 8/24/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({H2UserDAOTest.class,AccountCategoriesTest.class})
public class DBTestSuite {

}
