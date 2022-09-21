import java.util.Scanner;

/**
 * This class defines an implementation of the recursive acker function.
 */
public class AckerFunction {

    /**
     * Stores the number of spaces for the current desired level of indentation.
     */
    private static int spaces = 0;

    /**
     * Counts the number of invocations of the acker function.
     */
    private static int numberOfInvocations = 0;

    /**
     * Gets the number of invocations of the acker function, <code>numberOfInvocations</code>.
     * @return the number of invocations
     */
    public static int countOfInvocations(){
        return numberOfInvocations;
    }

    /**
     * Executes the recursive acker function, defined as acker(m, n) =
     * <ul><li>n + 1, if m = 0</li>
     * <li>acker(m - 1, 1), if n = 0</li>
     * <li>acker(m - 1, acker(m, n - 1)), otherwise</li></ul>
     * @param m the parameter m to be inputted into the acker function.
     * @param n the parameter n to be inputted into the acker function.
     * @return the result of the acker function.
     */
    public static int acker(int m, int n){
        // Initialize the results variable with 0.
        int result = 0;

        // Increment the number of invocations.
        numberOfInvocations++;

        // Print the spaces before the message.
        printSpaces();

        // Print the method entering message.
        System.out.printf("Enter method acker: m = %d, n = %d\n", m, n);

        // Check if m is equal to 0.
        if(m == 0) {
            // Acker(m, n) = n + 1.
            result = n + 1;
        }

        // Otherwise, check if n is equal to 0.
        else if(n == 0) {
            // Increment the number of spaces by 4.
            spaces += 4;

            // Acker(m, n) = Acker(m - 1, 1).
            result = acker(m - 1, 1);

            // Decrement the number of spaces by 4.
            spaces -= 4;
        }

        // Otherwise, use the final method call in the equation.
        else {
            // Increment the number of spaces by 4.
            spaces += 4;

            // Acker(m, n) = Acker(m - 1, Acker(m, n - 1)).
            result = acker(m - 1, acker(m, n - 1));

            // Decrement the number of spaces by 4.
            spaces -= 4;
        }

        // Print the spaces before the message.
        printSpaces();

        // Print the method leave message.
        System.out.printf("Leave method acker: acker(%d, %d) = %d\n", m, n, result);

        // Return the result.
        return result;
    }

    /**
     * Indent the trace messages according to how "deep" the current recursive call is.
     * Prints <code>spaces</code> number of spaces to the console.
     */
    private static void printSpaces(){
        for (int i = 0; i < spaces; i++)
            System.out.print(" ");
    }

    public static void main(String[] args) {
        // Initialize a Scanner object.
        Scanner scanner = new Scanner(System.in);

        // Initialize a variable to track if the input was valid.
        boolean validInput = true;

        // Initialize values m and n equal to 0.
        int m = 0, n = 0;

        // Attempt to get valid input from the user.
        do {
            // Reset the validInput variable.
            validInput = true;

            // Prompt the user for input.
            System.out.println("Input two integers separated by a space character (enter \"q\" to quit):");

            // Read the user's input.
            String input = scanner.nextLine();

            // Check if the user entered "q", and quit if so.
            if(input.equalsIgnoreCase("q")) System.exit(0);

            // Attempt to split the message by the space in the middle.
            String[] values = input.split(" ");

            // Ensure the input has only two values separated by a space.
            if(values.length != 2) {
                // Inform the user they must enter exactly two values separated by a space character.
                System.out.println("[Error] Input must contain exactly two integers separated by a space character.");

                // If there is not exactly 2 values separated by a space, attempt to get input again.
                validInput = false;
                continue;
            }

            // Attempt to parse the integers.
            try {
                m = Integer.parseInt(values[0]);
                n = Integer.parseInt(values[1]);
            } catch(NumberFormatException e) {
                // If there is an error parsing the integers, inform the user.
                System.out.println("[Error] Input must contain only integer values.");

                // Attempt to get input again.
                validInput = false;
                continue;
            }

            // Ensure the integers are nonnegative.
            if(m < 0 || n < 0) {
                // Inform the user the integers must be nonnegative.
                System.out.println("[Error] Inputted integers must be nonnegative.");

                // Attempt to get input again.
                validInput = false;
                continue;
            }
        } while(!validInput);

        // Close the scanner.
        scanner.close();

        // Call the recursive method acker(int, int).
        int result = acker(m, n);

        // Print the results.
        System.out.printf("\nTotal number of invocations = %d, result = %d", countOfInvocations(), result);

    }
}
