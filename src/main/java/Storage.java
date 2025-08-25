import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> load() {
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
