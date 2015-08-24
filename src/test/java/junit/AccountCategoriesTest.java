package junit;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Dauren_Altynbekov on 8/24/2015.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(AccountTest.class)
@Suite.SuiteClasses({H2UserDAOTest.class,H2AccountDAOTest.class,H2AccountTypeDAOTest.class})
public class AccountCategoriesTest {
}
