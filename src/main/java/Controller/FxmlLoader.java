package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class FxmlLoader {
    public Node getComponent(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            root.setUserData(loader);
            return root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
