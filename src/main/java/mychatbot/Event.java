package mychatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event that has a description, completion status, start and end dates.
 * Extends the Task class to include start and end dates.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event.
     * The start and end dates (parameter 'from' and 'to' respectively),
     * should be in the format 'd/M/yyyy HHmm'.
     * @param description
     * @param isDone
     * @param from
     * @param to
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Returns the string representation of the Event, formatted for displaying to the user.
     * @return
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the Event, formatted for file storage.
     * @return
     */
    @Override
    public String toDataString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
