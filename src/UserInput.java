package project1;

import java.util.List;
import java.util.Scanner;

/**
 * Controls all interaction with the user and console: prints menus and prompts,
 * reads what the user types, and calls the appropriate PasswordLibrary methods
 * based on what the user chooses to do.
 */
public class UserInput
{

    private final PasswordLibrary library;
    private final Scanner scanner;

    /**
     * Constructor for the PasswordLibrary library UserInput will use. The
     * Scanner reads from standard input (the console).
     */
    public UserInput(PasswordLibrary library)
    {
        this(library, new Scanner(System.in));
    }


    /**
     * Package-private constructor used by tests to inject a Scanner over a
     * scripted string of input instead of real console input.
     */
    UserInput(PasswordLibrary library, Scanner scanner)
    {
        this.library = library;
        this.scanner = scanner;
    }


    /**
     * The main loop that prints the menu, reads user input, connects to the
     * right handler, and repeats until the user quits.
     */
    public void run()
    {
        boolean running = true;
        while (running)
        {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice)
            {
                case "1":
                    handleAdd();
                    break;
                case "2":
                    handleGet();
                    break;
                case "3":
                    handleRemove();
                    break;
                case "4":
                    handleListAll();
                    break;
                case "5":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println(
                        "Invalid menu choice. Please enter a number 1-5.");
            }
        }
    }


    private void printMenu()
    {
        System.out.println();
        System.out.println("===== PaSSBooK Menu =====");
        System.out.println("1. Add a new website");
        System.out.println("2. Get a website's login info");
        System.out.println("3. Remove a website");
        System.out.println("4. List all websites");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }


    /**
     * Prompts the user for website, username, password. Passes each through
     * InputValidator and catches InvalidInputException, re- prompting for that
     * same field until it is valid.
     */
    public void handleAdd()
    {
        String websiteName = promptValid("Website name");
        String username = promptValid("Username");
        String password = promptValid("Password");

        Website website =
            new Website(websiteName, new Password(username, password));
        try
        {
            library.add(websiteName, website);
            System.out
                .println("Website \"" + websiteName + "\" added successfully.");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Prompts the user for a website name and calls PasswordLibrary.get().
     * Catches WebsiteNotFoundException and prints a message telling the user to
     * check their spelling.
     */
    public void handleGet()
    {
        System.out.print("Enter the website name: ");
        String websiteName = scanner.nextLine().trim();

        try
        {
            Website website = library.get(websiteName);
            Password p = website.getPassword();
            System.out.println("Website: " + website.getName());
            System.out.println("Username: " + p.getUsername());
            System.out.println("Password: " + p.getPassword());
        }
        catch (WebsiteNotFoundException e)
        {
            System.out.println(
                "That website was not found. Please check your spelling and try again.");
        }
    }


    /**
     * Prompts the user for a website name and a confirmation, then calls
     * PasswordLibrary.remove(). Catches WebsiteNotFoundException.
     */
    public void handleRemove()
    {
        System.out.print("Enter the website name to remove: ");
        String websiteName = scanner.nextLine().trim();

        System.out.print(
            "Are you sure you want to remove \"" + websiteName + "\"? (y/n): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("y"))
        {
            System.out.println("Removal cancelled.");
            return;
        }

        try
        {
            library.remove(websiteName);
            System.out.println("Website \"" + websiteName + "\" removed.");
        }
        catch (WebsiteNotFoundException e)
        {
            System.out.println(
                "That website was not found. Please check your spelling and try again.");
        }
    }


    /**
     * Calls PasswordLibrary.getAllEntries() and prints each entry.
     */
    public void handleListAll()
    {
        List<Website> all = library.getAllEntries();

        if (all.isEmpty())
        {
            System.out.println("No saved passwords.");
            return;
        }

        System.out.println("Saved websites:");
        for (Website w : all)
        {
            System.out.println(
                "- " + w.getName() + " (username: "
                    + w.getPassword().getUsername() + ")");
        }
    }


    /**
     * Prompts for a single field, re-prompting until InputValidator accepts the
     * value.
     */
    private String promptValid(String fieldName)
    {
        while (true)
        {
            System.out.print("Enter " + fieldName + ": ");
            String value = scanner.nextLine();
            try
            {
                return InputValidator.validateInput(fieldName, value);
            }
            catch (InvalidInputException e)
            {
                System.out.println(e.getMessage() + " Please try again.");
            }
        }
    }
}
