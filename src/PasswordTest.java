package project1;

import student.TestCase;

public class PasswordTest
    extends TestCase
{

    private Password password;

    public void setUp()
        throws Exception
    {
        password = new Password("user123", "hunter2");
    }

    // ---- getUsername() ----


    public void testGetUsernameNormal()
    {
        assertEquals("user123", password.getUsername());
    }

    // ---- getPassword() ----


    public void testGetPasswordNormal()
    {
        assertEquals("hunter2", password.getPassword());
    }

    // ---- setUsername(String) ----


    public void testSetUsernameNormal()
    {
        password.setUsername("newName");
        assertEquals("newName", password.getUsername());
    }


    public void testSetUsernameNull()
    {
        password.setUsername(null);
        assertNull(password.getUsername());
    }

    // ---- setPassword(String) ----


    public void testSetPasswordNormal()
    {
        password.setPassword("newPass");
        assertEquals("newPass", password.getPassword());
    }


    public void testSetPasswordNull()
    {
        password.setPassword(null);
        assertNull(password.getPassword());
    }

    // ---- no-arg constructor sanity check ----


    public void testDefaultConstructor()
    {
        Password p = new Password();
        assertNull(p.getUsername());
        assertNull(p.getPassword());
    }
}
