package mychatbot;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone True if the task is completed and false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date and time associated with the task.
     * For todos, this returns null.
     *
     * @return The date and time of the task, or null if not applicable.
     */
    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Returns the status icon representing whether the task is done.
     * "X" for done, " " for not done.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }

    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
