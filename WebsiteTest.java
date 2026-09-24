package project1;

import student.TestCase;

public class WebsiteTest
    extends TestCase
{

    private Website website;
    private Password password;

    public void setUp()
        throws Exception
    {
        password = new Password("user123", "hunter2");
        website = new Website("gmail.com", password);
    }

    // ---- setName(String) ----


    public void testSetNameNormal()
    {
        website.setName("gmail.com");
        assertEquals("gmail.com", website.getName());
    }


    public void testSetNameNull()
    {
        website.setName(null);
        assertNull(website.getName());
    }

    // ---- setPassword(Password) ----


    public void testSetPasswordNormal()
    {
        Password p = new Password("a", "b");
        website.setPassword(p);
        assertSame(p, website.getPassword());
    }


    public void testSetPasswordNull()
    {
        website.setPassword(null);
        assertNull(website.getPassword());
    }

    // ---- getName() ----


    public void testGetNameNormal()
    {
        website.setName("zoom.us");
        assertEquals("zoom.us", website.getName());
    }

    // ---- getPassword() ----


    public void testGetPasswordNormal()
    {
        website.setPassword(password);
        assertSame(password, website.getPassword());
    }

    // ---- no-arg constructor sanity check ----


    public void testDefaultConstructor()
    {
        Website w = new Website();
        assertNull(w.getName());
        assertNull(w.getPassword());
    }
}
