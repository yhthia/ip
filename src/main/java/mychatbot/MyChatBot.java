package mychatbot;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class of the application, which runs the main event loop.
 */
public class MyChatBot {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public MyChatBot() {
        ui = new Ui();
        storage = new Storage(DEFAULT_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (MyChatBotException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Initializes the main event loop.
     * Greets the user and scans the user's input and processes it accordingly.
     * The tasks from the user's input are also stored in a file and can be retrieved.
     * Any invalid commands or errors are reported to the user via the UI class.
     */
    public String getResponse(String userInput) {
        String response;
        String command = Parser.getCommandType(userInput);
        try {
            switch (command) {
            case "bye":
                return handleBye();
            case "list":
                return handleList();
            case "mark":
                return handleMark(userInput);
            case "unmark":
                return handleUnmark(userInput);
            case "todo":
                return handleTodo(userInput);
            case "deadline":
                return handleDeadline(userInput);
            case "event":
                return handleEvent(userInput);
            case "find":
                return handleFind(userInput);
            default:
                return ui.showError("Sorry, please try again.");
            }
        } catch (MyChatBotException e) {
            return ui.showError(e.getMessage());
        }
    }

    private String handleBye() {
        return ui.exit();
    }

    private String handleList() {
        return ui.printList(tasks);
    }

    private String handleMark(String userInput) throws MyChatBotException {
        int markIndex = Parser.getTaskIndex(userInput);
        tasks.getTask(markIndex).markAsDone();
        storage.save(tasks.getTasks());
        return ui.markTaskUi(tasks.getTask(markIndex));
    }

    private String handleUnmark(String userInput) throws MyChatBotException {
        int unmarkIndex = Parser.getTaskIndex(userInput);
        tasks.getTask(unmarkIndex).markAsNotDone();
        storage.save(tasks.getTasks());
        return ui.unmarkTaskUi(tasks.getTask(unmarkIndex));
    }

    private String handleTodo(String userInput) {
        String todoDesc = Parser.getDescription(userInput);
        Task todo = new Todo(todoDesc, false);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());
        return ui.addTaskUi(todo, tasks.size());
    }

    private String handleDeadline(String userInput) throws MyChatBotException {
        String[] deadlineParts = Parser.getDeadlineParts(userInput);
        Task deadline = new Deadline(deadlineParts[0], false, deadlineParts[1]);
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());
        return ui.addTaskUi(deadline, tasks.size());
    }

    private String handleEvent(String userInput) throws MyChatBotException {
        String[] eventParts = Parser.getEventParts(userInput);
        Task event = new Event(eventParts[0], false, eventParts[1], eventParts[2]);
        tasks.addTask(event);
        storage.save(tasks.getTasks());
        return ui.addTaskUi(event, tasks.size());
    }

    public String handleFind(String userInput) throws MyChatBotException {
        String keyword = Parser.getDescription(userInput);
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.printMatchingTasks(matchingTasks);
    }

    public void run() {
        Scanner scanner = new java.util.Scanner(System.in);
        System.out.println(ui.greet());

        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();
            String response = getResponse(userInput);
            System.out.println(response);
            if (Parser.getCommandType(userInput).equals("bye")) {
                isRunning = false;
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        new MyChatBot().run();
    }
}
