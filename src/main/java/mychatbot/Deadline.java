package mychatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline that has a description, completion status and a due date.
 * Extends the Task class to include a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline.
     * The due date (parameter 'by') should be in the format 'd/M/yyyy HHmm'.
     * @param description
     * @param isDone
     * @param by
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public LocalDateTime getDateTime() {
        return by;
    }

    /**
     * Returns the string representation of the Deadline, formatted for displaying to the user.
     * @return
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString()
                + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the Deadline, formatted for file storage.
     * @return
     */
    @Override
    public String toDataString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0")
                + " | " + description + " | " + by.format(formatter);
    }
}