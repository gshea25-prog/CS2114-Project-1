package project1;

/**
 * Validates and normalizes user-entered strings before they are stored in a
 * Password/Website or used as a PasswordLibrary key. Contains only static
 * methods -- validation does not depend on any state between calls.
 */
public class InputValidator
{

    /** Maximum allowed length for any single field. */
    public static final int MAX_LENGTH = 50;

    // Prevent instantiation -- this is a static utility class.
    private InputValidator()
    {
    }


    /**
     * Checks that value isn't empty, isn't too long, and doesn't contain
     * characters that would break PasswordLibrary.get(String key).
     *
     * @param fieldName
     *            the name of the field being validated, used in the exception
     *            message so the user knows what to fix
     * @param value
     *            the raw value to validate
     * @return the value, unchanged, if it passes validation
     * @throws InvalidInputException
     *             if value is null, empty/blank, too long, or contains invalid
     *             (control) characters
     */
    public static String validateInput(String fieldName, String value)
        throws InvalidInputException
    {
        if (value == null || value.trim().isEmpty())
        {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }

        if (value.length() > MAX_LENGTH)
        {
            throw new InvalidInputException(
                fieldName + " is too long (max " + MAX_LENGTH
                    + " characters).");
        }

        for (int i = 0; i < value.length(); i++)
        {
            char c = value.charAt(i);
            if (Character.isISOControl(c))
            {
                throw new InvalidInputException(
                    fieldName + " contains an invalid character.");
            }
        }

        return value;
    }
}
