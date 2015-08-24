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
public class H2UserDAOTest{
    H2UserDAO h2UserDAO = new H2UserDAO();

    @Test
    public void testGetAllUsers(){
        List<User> users = h2UserDAO.getAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void testAddUser(){
        h2UserDAO.add("SomeGuy", "123", Role.CLIENT, "lalala@mail.com");
        User someUser = h2UserDAO.findUser("SomeGuy", "123");
        Assert.assertEquals(someUser.getEmail(), "lalala@mail.com");
    }

    @Test
    public void testNegativeAddUser(){
        h2UserDAO.add("SomeGuy10000000000000000000000000000000000000000000", "123", Role.CLIENT, "lalala@mail.com");
        User someUser = h2UserDAO.findUser("SomeGuy10000000000000000000000000000000000000000000", "123");
        Assert.assertNull(someUser);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteUser(){
        User user = h2UserDAO.findUser("SomeGuy", "123");
        h2UserDAO.deleteByID(user.getId());
        User userAfterDeleting = h2UserDAO.findUserByID(user.getId());
        Assert.assertTrue(userAfterDeleting.equals(null));
    }

    @Test
    public void testNegativeDeleteUser(){
        h2UserDAO.deleteByID(-1);
        Assert.assertNull(h2UserDAO.findUserByID(-1));
    }
}
