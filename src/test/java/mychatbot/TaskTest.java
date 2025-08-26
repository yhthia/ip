package mychatbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    void testMarkAsDone() {
        Task task = new Todo("read book", false);
        assertFalse(task.isDone, "Task should not be marked as done on initialization.");
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done.");
    }

    @Test
    void testMarkAsNotDone() {
        Task task = new Todo("watch lecture", false);
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done.");
        task.markAsNotDone();
        assertFalse(task.isDone, "Task should not be marked as done.");
    }
}
