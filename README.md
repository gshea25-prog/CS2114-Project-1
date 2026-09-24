# PaSSBooK

A local, console-based password library. Store website -> username/password
pairs, look them up, list them, and remove them - all from the command line.

## Requirements

- Java 17 or later (JDK, not just a JRE - you need `javac` to compile)

Check your version:

```bash
java -version
javac -version
```

## Project structure

```
src/main/java/passbook/
    Password.java
    Website.java
    PasswordLibrary.java
    InputValidator.java
    InvalidInputException.java
    WebsiteNotFoundException.java
    UserInput.java
    PaSSBooKApp.java          <- contains main(), entry point
src/test/java/passbook/
    PasswordTest.java
    WebsiteTest.java
    PasswordLibraryTest.java
    InputValidatorTest.java
    UserInputTest.java
    PaSSBooKAppTest.java
pom.xml
```

## Compiling and running

### Option A - plain javac / java (no build tool required)

From the project's root directory:

```bash
# Compile every source file into an "out" folder
mkdir -p out
javac -d out src/main/java/passbook/*.java

# Run the program
java -cp out passbook.PaSSBooKApp
```

You should see the main menu appear in your terminal:

```
===== PaSSBooK =====
1. Add a website
2. Get a website's login info
3. Remove a website
4. List all websites
5. Quit
Choose an option:
```

Enter the number of the option you want and follow the prompts. Choose
`5` at any time to quit.

### Option B - Maven

If you have Maven installed:

```bash
# Compile
mvn compile

# Compile and run in one step
mvn compile exec:java -Dexec.mainClass=passbook.PaSSBooKApp

# Or build a runnable jar, then run it
mvn package
java -jar target/passbook.jar
```

## Running the tests

The test classes in `src/test/java/passbook/` are written against
`student.TestCase`, the JUnit-based testing library provided by the
course, not plain JUnit. `student.TestCase` isn't published on Maven
Central, so it isn't pulled in by `pom.xml` automatically - add your
course-provided jar to your project's build path (or import the project
into Eclipse with the CS2-Support project included) before compiling
or running the tests.

Once that jar is on your classpath:

- **Eclipse:** right-click a test class in the Package Explorer -> `Run As` -> `JUnit Test`.
- **Command line:** compile the test sources with both `out` (the compiled
  main classes) and the `student.TestCase` jar on the classpath, then run
  them with a JUnit runner, e.g.:

  ```bash
  javac -cp "out:/path/to/student-library.jar:/path/to/junit.jar" \
      -d out-test src/test/java/passbook/*.java

  java -cp "out:out-test:/path/to/student-library.jar:/path/to/junit.jar" \
      org.junit.runner.JUnitCore passbook.PasswordTest passbook.WebsiteTest \
      passbook.PasswordLibraryTest passbook.InputValidatorTest \
      passbook.UserInputTest passbook.PaSSBooKAppTest
  ```

  (Adjust the `:` path separator to `;` on Windows, and swap in the
  actual paths to your course's jars.)

## Notes

- `InvalidInputException` is a small additional class beyond the seven
  general classes named in the spec - it's required because
  `InputValidator.validateInput(...)` is specified with
  `throws InvalidInputException`.
- Design choice carried over from the spec: exceptions are handled by
  the *calling* class, not the class that throws them. `UserInput`
  catches `InvalidInputException`, `WebsiteNotFoundException`, and
  `IllegalArgumentException` - none of those classes catch their own
  exceptions internally.
