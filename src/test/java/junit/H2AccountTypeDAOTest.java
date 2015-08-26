package junit;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.H2AccountTypeDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.NotFoundException;

import java.util.List;

/**
 * Created by Dauren_Altynbekov on 8/24/2015.
 */
@Category(AccountTest.class)
public class H2AccountTypeDAOTest {

    private H2AccountTypeDAO h2AccountTypeDAO = new H2AccountTypeDAO();

    @Test
    public void testGetAllAccounts(){
        List<AccountType> accountsTypes = h2AccountTypeDAO.findAll();
        Assert.assertNotNull(accountsTypes);
    }

    @Test(expected = NotFoundException.class)
    public void testFindNotExistAccountType(){
        h2AccountTypeDAO.findAccTypeByID(-1);
    }
}
