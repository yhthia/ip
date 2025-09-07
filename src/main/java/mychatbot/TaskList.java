package mychatbot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int idx) throws MyChatBotException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new MyChatBotException("Task number is out of bounds.");
        }
        return tasks.get(idx);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public void sortChronologically() {
        tasks.sort(Comparator.comparing(
                task -> task.getDateTime() == null ? LocalDateTime.MAX : task.getDateTime()
        ));
    }
}
