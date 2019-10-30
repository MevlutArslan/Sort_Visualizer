package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sort Visualizer");
        primaryStage.setScene(new Scene(root, 1300, 800));
        primaryStage.show();
        root.setStyle("-fx-background-color: #a5a5a5 ;");
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
