package mychatbot;

import java.util.ArrayList;

public class Ui {
    public String greet() {
        return "Hello! I'm MyChatBot. \nWhat can I do for you?";
    }

    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public String addTaskUi(Task task, int size) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String printList(TaskList list) throws MyChatBotException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(list.getTask(i)).append("\n");
        }
        return stringBuilder.toString().trim();
    }

    public String printMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return stringBuilder.toString().trim();
    }

    public String markTaskUi(Task task) {
        return "Nice! I've marked this task as done:\n[X] " + task.description;
    }

    public String unmarkTaskUi(Task task) {
        return "OK, I've marked this task as not done yet:\n[ ] " + task.description;
    }

    public String showError(String error) {
        return error;
    }

    public String showLoadingError() {
        return "Error loading tasks from file.";
    }
}
