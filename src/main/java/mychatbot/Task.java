package mychatbot;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return null;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

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
