package mychatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Responsible for reading from and writing to the task data file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where the tasks are saved in.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }


    /**
     * Loads tasks from the file.
     * If the file or its parent directory does not exist, they will be created.
     * @return An ArrayList of Tasks loaded from the file.
     * @throws MyChatBotException if there is an error reading the file.
     */
    public ArrayList<Task> load() throws MyChatBotException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file and/or directory: " + e.getMessage());
        }
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];
                switch(type) {
                case "T":
                    tasks.add(new Todo(desc, isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(desc, isDone, parts[3]));
                    break;
                case "E":
                    tasks.add(new Event(desc, isDone, parts[3], parts[4]));
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks into file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given task list into a file.
     * Each task is serialised into a string and written in the file as a new line.
     * @param tasks The list of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            Files.createDirectories(filePath.getParent());
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
                for (Task task : tasks) {
                    bufferedWriter.write(task.toDataString());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks into file: " + e.getMessage());
        }
    }
}
