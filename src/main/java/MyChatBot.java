import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;


public class MyChatBot {
    private final Scanner input = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();


    private void greet() {
        System.out.println("Hello! I'm MyChatBot.");
        System.out.println("What can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private void markTask(int task) throws MyChatBotException {
        if (task < 1 || task > list.size()) {
            throw new MyChatBotException("Task number " + task + " does not exist.");
        }
        Task doneTask = list.get(task - 1);
        doneTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + doneTask.description);
    }

    private void unmarkTask(int task) throws MyChatBotException {
        if (task < 1 || task > list.size()) {
            throw new MyChatBotException("Task number " + task + " does not exist.");
        }
        Task undoneTask = list.get(task - 1);
        undoneTask.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + undoneTask.description);
    }

    private void echoHelper() throws MyChatBotException {
        while (true) {
            try {
                String userInput = input.nextLine();
                if (userInput.equals("bye")) {
                    exit();
                    break;
                } else if (userInput.equals("list")) {
                    printList();
                } else if (userInput.startsWith("mark ")) {
                    try {
                        int task = Integer.parseInt(userInput.substring(5).trim());
                        markTask(task);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    } catch (MyChatBotException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (userInput.startsWith("unmark ")) {
                    try {
                        int task = Integer.parseInt(userInput.substring(7).trim());
                        unmarkTask(task);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    } catch (MyChatBotException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (userInput.startsWith("todo ")) {
                    String desc = userInput.substring(5).trim();
                    if (desc.isEmpty()) {
                        throw new MyChatBotException("Please provide a description for your Todo.");
                    }
                    addTask(new Todo(desc));
                } else if (userInput.startsWith("deadline ")) {
                    String body = userInput.substring(9).trim();
                    int byIdx = body.indexOf(" /by ");
                    if (byIdx == -1) {
                        throw new MyChatBotException("Please provide a deadline using '/by'.");
                    }
                    String desc = body.substring(0, byIdx);
                    String by = body.substring(byIdx + 5);
                    if (desc.isEmpty() || by.isEmpty()) {
                        throw new MyChatBotException("Please provide a description and a deadline using '/by'.");
                    }
                    addTask(new Deadline(desc.trim(), by.trim()));
                } else if (userInput.startsWith("event ")) {
                    String body = userInput.substring(6).trim();
                    int fromIdx = body.indexOf(" /from ");
                    int toIdx = body.indexOf(" /to ");
                    if (body.isEmpty() || fromIdx == -1 || toIdx == -1 || toIdx < fromIdx) {
                        throw new MyChatBotException("Please provide a start time using '/from' and an end time using '/to'.");
                    }
                    String desc = body.substring(0, fromIdx);
                    String from = body.substring(fromIdx + 7, toIdx);
                    String to = body.substring(toIdx + 5);
                    if (desc.isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
                        throw new MyChatBotException("Please provide a description, a start time using '/from' and an end time using '/to'.");
                    }
                    addTask(new Event(desc.trim(), from.trim(), to.trim()));
                } else {
                    throw new MyChatBotException("Sorry, please try again.");
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    private void echo() {
        try {
            echoHelper();
        } catch (MyChatBotException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        MyChatBot chatbot = new MyChatBot();
        chatbot.greet();
        chatbot.echo();
    }
}
