package junit;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.dao.H2AccountDAO;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Dauren_Altynbekov on 8/11/2015.
 */
public class H2AccountDaoTest {
    H2AccountDAO h2AccountDAO = new H2AccountDAO();

    @Test(expected = SQLException.class)
    public void testCatchSQLExceptionThenFindAccountsNonexistentUser(){
        h2AccountDAO.findAllByUserID(-1);
    }

    @Test
    public  void testCatchSQLExceptionThenTryingAddAccountToNonexistentUser(){
        Account account = new Account("-1","1",1000);
    }
}
