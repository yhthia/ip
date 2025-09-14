package mychatbot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Creates a TaskList with the specified tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the list of all tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param idx The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws MyChatBotException If the index is out of bounds.
     */
    public Task getTask(int idx) throws MyChatBotException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new MyChatBotException("Task number is out of bounds.");
        }
        return tasks.get(idx);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks whose description contains the given keyword (case-insensitive).
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            return matchingTasks;
        }
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Sorts the tasks chronologically by their datetime.
     * Tasks without a date/time will be placed at the bottom.
     */
    public void sortChronologically() {
        tasks.sort(Comparator.comparing(
                task -> task.getDateTime() == null ? LocalDateTime.MAX : task.getDateTime()
        ));
    }
}
