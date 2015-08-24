package junit;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.dao.H2AccountDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Dauren_Altynbekov on 8/24/2015.
 */
@RunWith(Parameterized.class)
@Category(AccountTest.class)
public class H2AccountDAOTest {

    private int userID;

    private H2AccountDAO h2AccountDAO = new H2AccountDAO();

    public H2AccountDAOTest(int userID){
        this.userID = userID;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{33}, {36}});
    }

    @Test
    public void testGetAllAccounts(){
        List<Account> accounts = h2AccountDAO.findAllByUserID(userID);
        Assert.assertNotNull(accounts);
    }
}
