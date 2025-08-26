public class Ui {
    public void greet() {
        System.out.println("Hello! I'm MyChatBot.");
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addTaskUi(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.getTask(i));
        }
    }

    public void markTaskUi(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + task.description);
    }

    public void unmarkTaskUi(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + task.description);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
