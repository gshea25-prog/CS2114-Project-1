package project1;

import student.TestCase;

public class InputValidatorTest
    extends TestCase
{

    // ---- validateInput(String, String) ----

    public void testValidateInputNormal()
        throws InvalidInputException
    {
        assertEquals(
            "user123",
            InputValidator.validateInput("username", "user123"));
    }


    public void testValidateInputEmptyThrows()
    {
        try
        {
            InputValidator.validateInput("username", "");
            fail("Expected InvalidInputException for empty input");
        }
        catch (InvalidInputException e)
        {
            assertTrue(e.getMessage().contains("username"));
        }
    }


    public void testValidateInputNullThrows()
    {
        try
        {
            InputValidator.validateInput("username", null);
            fail("Expected InvalidInputException for null input");
        }
        catch (InvalidInputException e)
        {
            assertTrue(e.getMessage().contains("username"));
        }
    }


    public void testValidateInputTooLongThrows()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < InputValidator.MAX_LENGTH + 1; i++)
        {
            sb.append("a");
        }
        try
        {
            InputValidator.validateInput("username", sb.toString());
            fail("Expected InvalidInputException for too-long input");
        }
        catch (InvalidInputException e)
        {
            assertTrue(e.getMessage().contains("username"));
        }
    }
}
