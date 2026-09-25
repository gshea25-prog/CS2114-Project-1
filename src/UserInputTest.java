package project1;

import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserInputTest
    extends TestCase
{

    private PasswordLibrary library;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    public void setUp()
        throws Exception
    {
        library = new PasswordLibrary();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }


    public void tearDown()
        throws Exception
    {
        System.setOut(originalOut);
    }


    /** Builds a UserInput whose "console" reads from a scripted string. */
    private UserInput buildInput(String script)
    {
        return new UserInput(library, new Scanner(script));
    }

    // ---- run() ----


    public void testRunAddThenQuit()
    {
        UserInput input = buildInput("1\ngmail.com\nuser1\npass1\n5\n");
        input.run();
        assertTrue(library.contains("gmail.com"));
    }


    public void testRunInvalidMenuChoice()
    {
        UserInput input = buildInput("9\n5\n");
        input.run();
        String output = outContent.toString();
        assertTrue(output.contains("Invalid menu choice"));
    }

    // ---- handleAdd() ----


    public void testHandleAddNormal()
    {
        UserInput input = buildInput("gmail.com\nuser1\npass1\n");
        input.handleAdd();
        assertEquals(1, library.size());
    }


    public void testHandleAddEmptyThenValid()
    {
        // Empty website name first, then a valid one; username/password valid
        // immediately.
        UserInput input = buildInput("\ngmail.com\nuser1\npass1\n");
        input.handleAdd();
        assertEquals(1, library.size());
    }

    // ---- handleGet() ----


    public void testHandleGetNormal()
    {
        library.add(
            "gmail.com",
            new Website("gmail.com", new Password("user1", "pass1")));
        UserInput input = buildInput("gmail.com\n");
        input.handleGet();
        String output = outContent.toString();
        assertTrue(output.contains("user1"));
        assertTrue(output.contains("pass1"));
    }


    public void testHandleGetNotFound()
    {
        UserInput input = buildInput("nosuchsite.com\n");
        input.handleGet();
        String output = outContent.toString();
        assertTrue(output.contains("not found"));
    }

    // ---- handleRemove() ----


    public void testHandleRemoveNormal()
    {
        library.add(
            "gmail.com",
            new Website("gmail.com", new Password("user1", "pass1")));
        UserInput input = buildInput("gmail.com\ny\n");
        input.handleRemove();
        assertEquals(0, library.size());
    }


    public void testHandleRemoveNotFound()
    {
        UserInput input = buildInput("nosuchsite.com\ny\n");
        input.handleRemove();
        String output = outContent.toString();
        assertTrue(output.contains("not found"));
        assertEquals(0, library.size());
    }

    // ---- handleListAll() ----


    public void testHandleListAllNormal()
    {
        library.add(
            "gmail.com",
            new Website("gmail.com", new Password("u1", "p1")));
        library
            .add("zoom.us", new Website("zoom.us", new Password("u2", "p2")));
        UserInput input = buildInput("");
        input.handleListAll();
        String output = outContent.toString();
        assertTrue(output.contains("gmail.com"));
        assertTrue(output.contains("zoom.us"));
    }


    public void testHandleListAllEmpty()
    {
        UserInput input = buildInput("");
        input.handleListAll();
        String output = outContent.toString();
        assertTrue(output.contains("No saved passwords"));
    }
}
