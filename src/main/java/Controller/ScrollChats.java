package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ScrollChats {
    @FXML
    private ScrollPane scrollChats;
    @FXML
    private VBox chatsBox;

    public final FxmlLoader loader = new FxmlLoader();

    public void initialize(){
        chatsBox.prefHeightProperty().bind(scrollChats.heightProperty());
        chatsBox.prefWidthProperty().bind(scrollChats.widthProperty());
        chatsBox.setMaxWidth(Region.USE_PREF_SIZE);
        VBox.setVgrow(chatsBox, Priority.ALWAYS);
        scrollChats.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }



}
