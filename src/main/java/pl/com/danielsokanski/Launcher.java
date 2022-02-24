package pl.com.danielsokanski;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Launcher extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(parent, 981, 594);
        stage.setScene(scene);
        stage.setTitle("POGODA NA WAKACJE");
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}