package codes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/gameBoard.fxml")));
        setStage(primaryStage);
        stage = primaryStage;
        primaryStage.setTitle("connect 4 game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public Stage getStage(){
        return stage;
    }
    private void setStage(Stage stage){
        Main.stage = stage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
