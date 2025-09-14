package mychatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testFindTasks_MatchingKeywordMultipleTasks() {
        Task task1 = new Todo("Read book", false);
        Task task2 = new Todo("Write code", false);
        Task task3 = new Todo("Read articles", false);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        ArrayList<Task> matchingTasks = taskList.findTasks("read");
        assertEquals(2, matchingTasks.size(), "There should be 2 tasks matching the keyword 'read'.");
        assertTrue(matchingTasks.contains(task1), "Matching tasks should include 'Read book'.");
        assertTrue(matchingTasks.contains(task3), "Matching tasks should include 'Read articles'.");
    }

    @Test
    void testFindTasks_NoMatchingKeyword() {
        Task task1 = new Todo("Read book", false);
        Task task2 = new Todo("Write code", false);
        taskList.addTask(task1);
        taskList.addTask(task2);

        ArrayList<Task> matchingTasks = taskList.findTasks("Play");
        assertTrue(matchingTasks.isEmpty(), "No tasks should match the keyword 'Play'.");
    }

    @Test
    void testFindTasks_EmptyKeyword() {
        Task task1 = new Todo("Read book", false);
        Task task2 = new Todo("Write code", false);
        taskList.addTask(task1);
        taskList.addTask(task2);

        ArrayList<Task> matchingTasks = taskList.findTasks("");
        assertEquals(0, matchingTasks.size(), "No tasks should match an empty keyword.");
    }

    @Test
    void testFindTasks_CaseInsensitiveMatch() {
        Task task1 = new Todo("Read book", false);
        Task task2 = new Todo("Write code", false);
        taskList.addTask(task1);
        taskList.addTask(task2);

        ArrayList<Task> matchingTasks = taskList.findTasks("READ");
        assertEquals(1, matchingTasks.size(), "Search should match tasks case-insensitively.");
        assertTrue(matchingTasks.contains(task1), "Matching tasks should include 'Read book'.");
    }

    @Test
    void testSortChronologically_ValidTasks() {
        Task task1 = new Deadline("Submit report", false, "15/9/2025 1000");
        Task task2 = new Event("Team meeting", false, "14/9/2025 1400", "14/9/2025 1500");
        Task task3 = new Todo("General task", false);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        taskList.sortChronologically();
        assertEquals(task2, taskList.getTasks().get(0), "Task2 should be the first task chronologically.");
        assertEquals(task1, taskList.getTasks().get(1), "Task1 should be the second task chronologically.");
        assertEquals(task3, taskList.getTasks().get(2), "Task3 (no date) should be the last task.");
    }

    @Test
    void testSortChronologically_AllTasksWithoutDates() {
        Task task1 = new Todo("Task 1", false);
        Task task2 = new Todo("Task 2", false);
        Task task3 = new Todo("Task 3", false);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        taskList.sortChronologically();
        assertEquals(task1, taskList.getTasks().get(0), "Order should remain unchanged for tasks without dates.");
        assertEquals(task2, taskList.getTasks().get(1), "Order should remain unchanged for tasks without dates.");
        assertEquals(task3, taskList.getTasks().get(2), "Order should remain unchanged for tasks without dates.");
    }

    @Test
    void testSortChronologically_EmptyTaskList() {
        taskList.sortChronologically();
        assertTrue(taskList.getTasks().isEmpty(), "Sorting an empty task list should not throw errors.");
    }

    @Test
    void testSortChronologically_MixedDatesAndNoDates() {
        Task task1 = new Deadline("Submit report", false, "16/9/2025 1000");
        Task task2 = new Event("Team meeting", false, "14/9/2025 1400", "14/9/2025 1500");
        Task task3 = new Todo("General task", false);
        Task task4 = new Deadline("Another report", false, "17/9/2025 1200");
        taskList.addTask(task3);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task4);

        taskList.sortChronologically();

        assertEquals(task2, taskList.getTasks().get(0), "Task2 should be the first task chronologically.");
        assertEquals(task1, taskList.getTasks().get(1), "Task1 should be the second task chronologically.");
        assertEquals(task4, taskList.getTasks().get(2), "Task4 should be the third task chronologically.");
        assertEquals(task3, taskList.getTasks().get(3), "Task3 (no date) should be the last task.");
    }
}