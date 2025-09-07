package mychatbot;

import java.time.LocalDateTime;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String toString() {
        return  "[T][" + getStatusIcon() + "] " + description;
    }
}
