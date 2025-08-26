package mychatbot;

import java.util.Scanner;

/**
 * Main class of the application, which runs the main event loop.
 */
public class MyChatBot {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public MyChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MyChatBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initializes the main event loop.
     * Greets the user and scans the user's input and processes it accordingly.
     * The tasks from the user's input are also stored in a file and can be retrieved.
     * Any invalid commands or errors are reported to the user via the UI class.
     */
    public void run() {
        ui.greet();
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String userInput = input.nextLine();
            String command = Parser.getCommandType(userInput);

            try {
                switch (command) {
                case "bye":
                    ui.exit();
                    isRunning = false;
                    break;
                case "list":
                    ui.printList(tasks);
                    break;
                case "mark":
                    int markIndex = Parser.getTaskIndex(userInput);
                    tasks.getTask(markIndex).markAsDone();
                    storage.save(tasks.getTasks());
                    ui.markTaskUi(tasks.getTask(markIndex));
                    break;
                case "unmark":
                    int unmarkIndex = Parser.getTaskIndex(userInput);
                    tasks.getTask(unmarkIndex).markAsNotDone();
                    storage.save(tasks.getTasks());
                    ui.unmarkTaskUi(tasks.getTask(unmarkIndex));
                    break;
                case "todo":
                    String todoDesc = Parser.getDescription(userInput);
                    Task todo = new Todo(todoDesc, false);
                    tasks.addTask(todo);
                    storage.save(tasks.getTasks());
                    ui.addTaskUi(todo, tasks.size());
                    break;
                case "deadline":
                    String[] deadlineParts = Parser.getDeadlineParts(userInput);
                    Task deadline = new Deadline(deadlineParts[0], false, deadlineParts[1]);
                    tasks.addTask(deadline);
                    storage.save(tasks.getTasks());
                    ui.addTaskUi(deadline, tasks.size());
                    break;
                case "event":
                    String[] eventParts = Parser.getEventParts(userInput);
                    Task event = new Event(eventParts[0], false, eventParts[1], eventParts[2]);
                    tasks.addTask(event);
                    storage.save(tasks.getTasks());
                    ui.addTaskUi(event, tasks.size());
                    break;
                default:
                    ui.showError("Sorry, please try again.");
                }
            } catch (MyChatBotException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new MyChatBot("data/tasks.txt").run();
    }
}
