package mychatbot;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for MyChatBot using FXML.
 */
public class Main extends Application {

    private MyChatBot myChatBot = new MyChatBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("MyChatBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMyChatBot(myChatBot);  // inject the MyChatBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

