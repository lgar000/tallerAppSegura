package edu.escuelaing.arem.ASE.app;

import org.junit.Before;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        user = new User();
    }

    @Test
    public void testIsUserValidJohn() throws NoSuchAlgorithmException {
        assertTrue(user.isUser("john", "1234"));
    }

    @Test
    public void testIsUserInvalid() throws NoSuchAlgorithmException {
        assertFalse(user.isUser("ivan", "hola"));
    }

    @Test
    public void testIsUserValidJane() throws NoSuchAlgorithmException {
        assertTrue(user.isUser("jane", "5678"));
    }
    @Test
    public void testIsNotUser() throws NoSuchAlgorithmException {
        assertFalse(user.isUser("maria", "password"));
    }

}
