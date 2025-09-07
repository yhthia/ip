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
        assert userInput != null : "User input should not be null";
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
            case "sort":
                return handleSort();
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

    private String handleList() throws MyChatBotException {
        return ui.printList(tasks);
    }

    private String handleMark(String userInput) throws MyChatBotException {
        int markIndex = Parser.getTaskIndex(userInput);
        assert markIndex < tasks.size() : "Mark index out of bounds";
        tasks.getTask(markIndex).markAsDone();
        storage.save(tasks.getTasks());
        return ui.markTaskUi(tasks.getTask(markIndex));
    }

    private String handleUnmark(String userInput) throws MyChatBotException {
        int unmarkIndex = Parser.getTaskIndex(userInput);
        assert unmarkIndex < tasks.size() : "Unmark index out of bounds";
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
        assert deadlineParts.length == 2: "Deadline parts should have description and due date";
        Task deadline = new Deadline(deadlineParts[0], false, deadlineParts[1]);
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());
        return ui.addTaskUi(deadline, tasks.size());
    }

    private String handleEvent(String userInput) throws MyChatBotException {
        String[] eventParts = Parser.getEventParts(userInput);
        assert eventParts.length == 3: "Event parts should have description, start and end time";
        Task event = new Event(eventParts[0], false, eventParts[1], eventParts[2]);
        tasks.addTask(event);
        storage.save(tasks.getTasks());
        return ui.addTaskUi(event, tasks.size());
    }

    private String handleFind(String userInput) throws MyChatBotException {
        String keyword = Parser.getDescription(userInput);
        assert !keyword.isEmpty() : "Keyword should not be empty";
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.printMatchingTasks(matchingTasks);
    }

    private String handleSort() throws MyChatBotException {
        tasks.sortChronologically();
        return ui.printList(tasks);
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
