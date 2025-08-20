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

    private void markTask(int task) {
        if (task >= 1 && task <= list.size()) {
            Task doneTask = list.get(task - 1);
            doneTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + doneTask.description);

        }
    }

    private void unmarkTask(int task) {
        if (task >= 1 && task <= list.size()) {
            Task undoneTask = list.get(task - 1);
            undoneTask.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + undoneTask.description);
        }
    }

    private void echo() {
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                exit();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.startsWith("mark ")) {
                int task = Integer.parseInt(userInput.substring(5).trim());
                markTask(task);
            } else if (userInput.startsWith("unmark ")) {
                int task = Integer.parseInt(userInput.substring(6).trim());
                unmarkTask(task);
            } else if (userInput.startsWith("todo ")) {
                String desc = userInput.substring(5).trim();
                addTask(new Todo(desc));
            } else if (userInput.startsWith("deadline ")) {
                String body = userInput.substring(9).trim();
                int byIdx = body.indexOf(" /by ");
                if (byIdx != -1) {
                    String desc = body.substring(0, byIdx);
                    String by = body.substring(byIdx + 5);
                    addTask(new Deadline(desc.trim(), by.trim()));
                } else {
                    addTask(new Deadline(body, ""));
                }
            } else if (userInput.startsWith("event ")) {
                String body = userInput.substring(6).trim();
                int fromIdx = body.indexOf(" /from ");
                int toIdx = body.indexOf(" /to ");
                if (fromIdx != -1 && toIdx != -1 && toIdx > fromIdx) {
                    String desc = body.substring(0, fromIdx);
                    String from = body.substring(fromIdx + 7, toIdx);
                    String to = body.substring(toIdx + 5);
                    addTask(new Event(desc.trim(), from.trim(), to.trim()));
                } else {
                    addTask(new Event(body, "", ""));
                }
            } else {
                addTask(new Todo(userInput));
            }
        }
    }


    public static void main(String[] args) {
        MyChatBot chatbot = new MyChatBot();
        chatbot.greet();
        chatbot.echo();
    }
}
