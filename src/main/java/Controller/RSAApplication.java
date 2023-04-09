package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RSAApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RSAApplication.class.getResource("Scenes/chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("RSA - Chat");
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Styles/main-style.css")).toExternalForm());
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(1350);
        stage.setHeight(700);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}