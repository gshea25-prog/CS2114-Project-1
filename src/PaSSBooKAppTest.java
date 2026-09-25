package project1;

import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class PaSSBooKAppTest
    extends TestCase
{

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    public void setUp()
        throws Exception
    {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }


    public void tearDown()
        throws Exception
    {
        System.setOut(originalOut);
    }


    // The spec notes PaSSBooKApp needs no dedicated test methods since it
    // only wires the PasswordLibrary and UserInput together and starts
    // the loop. This test just confirms that wiring doesn't blow up and
    // that the app actually runs the UserInput it was given, immediately
    // quitting via a scripted "5".
    public void testConstructorStartsAndRunsToQuit()
    {
        PasswordLibrary library = new PasswordLibrary();
        UserInput input = new UserInput(library, new Scanner("5\n"));

        PaSSBooKApp app = new PaSSBooKApp(input);

        assertNotNull(app);
        assertTrue(outContent.toString().contains("Goodbye"));
    }
}
