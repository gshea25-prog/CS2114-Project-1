package project1;

import student.TestCase;
import java.util.List;

public class PasswordLibraryTest
    extends TestCase
{

    private PasswordLibrary library;

    public void setUp()
        throws Exception
    {
        library = new PasswordLibrary();
    }

    // ---- contains(String) ----


    public void testContainsTrue()
    {
        library
            .add("gmail.com", new Website("gmail.com", new Password("u", "p")));
        assertTrue(library.contains("gmail.com"));
    }


    public void testContainsFalse()
    {
        assertFalse(library.contains("nosuchsite.com"));
    }

    // ---- add(String, Website) ----


    public void testAddNormal()
    {
        library
            .add("gmail.com", new Website("gmail.com", new Password("u", "p")));
        assertEquals(1, library.size());
    }


    public void testAddDuplicateThrows()
    {
        library
            .add("gmail.com", new Website("gmail.com", new Password("u", "p")));
        try
        {
            library.add(
                "gmail.com",
                new Website("gmail.com", new Password("u2", "p2")));
            fail("Expected IllegalArgumentException for duplicate key");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(1, library.size());
        }
    }

    // ---- remove(String) ----


    public void testRemoveNormal()
    {
        Website w = new Website("gmail.com", new Password("u", "p"));
        library.add("gmail.com", w);
        Website removed = library.remove("gmail.com");
        assertSame(w, removed);
        assertEquals(0, library.size());
    }


    public void testRemoveNotFoundThrows()
    {
        try
        {
            library.remove("nosuchsite.com");
            fail("Expected WebsiteNotFoundException");
        }
        catch (WebsiteNotFoundException e)
        {
            assertEquals(0, library.size());
        }
    }

    // ---- get(String) ----


    public void testGetNormal()
    {
        Website w = new Website("gmail.com", new Password("u", "p"));
        library.add("gmail.com", w);
        assertSame(w, library.get("gmail.com"));
    }


    public void testGetNotFoundThrows()
    {
        try
        {
            library.get("nosuchsite.com");
            fail("Expected WebsiteNotFoundException");
        }
        catch (WebsiteNotFoundException e)
        {
            // expected
        }
    }

    // ---- clear() ----


    public void testClearNormal()
    {
        library.add("a.com", new Website("a.com", new Password("u", "p")));
        library.add("b.com", new Website("b.com", new Password("u", "p")));
        library.add("c.com", new Website("c.com", new Password("u", "p")));
        library.clear();
        assertEquals(0, library.size());
    }


    public void testClearEmptyLibrary()
    {
        library.clear();
        assertEquals(0, library.size());
    }

    // ---- getAllEntries() ----


    public void testGetAllEntriesNormal()
    {
        library.add("a.com", new Website("a.com", new Password("u", "p")));
        library.add("b.com", new Website("b.com", new Password("u", "p")));
        List<Website> all = library.getAllEntries();
        assertEquals(2, all.size());
    }


    public void testGetAllEntriesEmpty()
    {
        List<Website> all = library.getAllEntries();
        assertNotNull(all);
        assertTrue(all.isEmpty());
    }

    // ---- size() ----


    public void testSizeNormal()
    {
        library.add("a.com", new Website("a.com", new Password("u", "p")));
        library.add("b.com", new Website("b.com", new Password("u", "p")));
        assertEquals(2, library.size());
    }


    public void testSizeEmpty()
    {
        assertEquals(0, library.size());
    }
}
