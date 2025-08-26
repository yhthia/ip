package mychatbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void testGetTaskIndex() throws MyChatBotException {
        String input = "mark 3";
        assertEquals(2, Parser.getTaskIndex(input));
    }

    @Test
    void testGetTaskIndexInvalidInput() {
        String input = "mark a";
        Exception exception = assertThrows(MyChatBotException.class, () -> {
            Parser.getTaskIndex(input);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    void testGetTaskIndexNegativeNumber() {
        String input = "mark -1";
        Exception exception = assertThrows(MyChatBotException.class, () -> {
            Parser.getTaskIndex(input);
        });
        assertEquals("Task number must be positive.", exception.getMessage());
    }
}
