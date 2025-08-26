package mychatbot;

/**
 * Provides static methods for parsing users' input based on task type.
 */
public class Parser {
    /**
     * Extracts the command from the user input, which is the first word of the input.
     * @param input The raw input string.
     * @return The command.
     */
    public static String getCommandType(String input) {
        String[] tokens = input.trim().split(" ", 2);
        return tokens[0];
    }

    /**
     * Parses the task index from the user input.
     * Converts it into a zero based index.
     * @param input The raw input string.
     * @return The new task index.
     * @throws MyChatBotException If the format or number of the input string is invalid.
     */
    public static int getTaskIndex(String input) throws MyChatBotException {
        try {
            String[] tokens = input.trim().split(" ");
            if (tokens.length < 2) {
                throw new MyChatBotException("Invalid format, it should be: 'mark <number>'");
            }
            int idx = Integer.parseInt(tokens[1]) - 1;
            if (idx < 0) {
                throw new MyChatBotException("Task number must be positive.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new MyChatBotException("Invalid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MyChatBotException("Task number is out of bounds.");
        }
    }

    /**
     * Extracts the description of the Todo from the user input.
     * @param input The raw input string.
     * @return The description of the Todo in the user input.
     */
    public static String getDescription(String input) {
        return input.substring(input.indexOf(" ") + 1).trim();
    }

    /**
     * Extracts the description and deadline of the Deadline from the user input.
     * @param input The raw input string.
     * @return The description and deadline of the Deadline in the user input as String[].
     */
    public static String[] getDeadlineParts(String input) {
        String body = input.substring(8).trim();
        int byIdx = body.indexOf(" /by ");
        String desc = body.substring(0, byIdx);
        String by = body.substring(byIdx + 5);
        return new String[]{desc.trim(), by.trim()};
    }

    /**
     * Extracts the description, start and end times of the Event from the user input.
     * @param input The raw input string.
     * @return The description, start and end times of the Event in the user input as String[].
     */
    public static String[] getEventParts(String input) {
        String body = input.substring(5).trim();
        int fromIdx = body.indexOf(" /from ");
        int toIdx = body.indexOf(" /to ");
        String desc = body.substring(0, fromIdx);
        String from = body.substring(fromIdx + 7, toIdx);
        String to = body.substring(toIdx + 5);
        return new String[]{desc.trim(), from.trim(), to.trim()};
    }
}
