package project1;

/**
 * Thrown by {@link InputValidator} when a piece of user input fails validation
 * (empty, too long, or contains characters that would break lookups in
 * {@link PasswordLibrary}). This is a checked exception (extends Exception, not
 * RuntimeException) because the spec calls for callers -- namely UserInput --
 * to explicitly catch it and re-prompt the user.
 */
public class InvalidInputException
    extends Exception
{

    // ----------------------------------------------------------
    /**
     * Create a new InvalidInputException object.
     * @param message
     */
    public InvalidInputException(String message)
    {
        super(message);
    }
}
