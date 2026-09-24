package project1;

/**
 * The entry point of the program. Creates the PasswordLibrary on the first run,
 * hands it to a UserInput, and starts the console interaction.
 */
public class PaSSBooKApp
{

    /**
     * Constructor for the UserInput class that starts the program and
     * interaction with the user.
     */
    public PaSSBooKApp(UserInput input)
    {
        input.run();
    }


    public static void main(String[] args)
    {
        PasswordLibrary library = new PasswordLibrary();
        UserInput input = new UserInput(library);
        new PaSSBooKApp(input);
    }
}
