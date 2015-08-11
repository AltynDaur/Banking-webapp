package junit;

import com.epam.javalab.webapp.dao.H2UserDAO;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Created by Dauren_Altynbekov on 8/11/2015.
 */
@RunWith(JUnit4.class)
public class H2UserDAOTest {
    H2UserDAO h2UserDAO = new H2UserDAO();

    @Test
    public void testGetAllUsers(){
        List<User> users = h2UserDAO.getAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void testAddUser(){
        List<User> users = h2UserDAO.getAll();
        h2UserDAO.add("SomeGuy","123", Role.CLIENT,"lalala@mail.com");
        List<User> usersAfterAdding  = h2UserDAO.getAll();
        Assert.assertTrue(usersAfterAdding.size() - users.size() == 1);
    }

    @Test
    public void testDeleteUser(){
        List<User> users = h2UserDAO.getAll();
        User user = h2UserDAO.findUser("SomeGuy", "123");
        h2UserDAO.deleteByID(user.getId());
        List<User> usersAfterDeleting = h2UserDAO.getAll();
        Assert.assertTrue(users.size() - usersAfterDeleting.size() == 1);
    }
}
