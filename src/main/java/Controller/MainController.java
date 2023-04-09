package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainController {
    @FXML
    private VBox chatsContainer;
    @FXML
    private VBox historyContainer;
    @FXML
    private StackPane mainContainer;

    @FXML
    private VBox chatsZone;

    @FXML
    private TextField sendMessageField;

    private final FxmlLoader loader = new FxmlLoader();

    public void initialize(){
        historyContainer.prefHeightProperty().bind(mainContainer.heightProperty());
        historyContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.3));

        chatsContainer.prefHeightProperty().bind(mainContainer.heightProperty());
        chatsContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.71));

        VBox chats = (VBox) loader.getComponent("Components/chats-component.fxml");

        chats.prefHeightProperty().bind(chatsZone.heightProperty());
        chats.prefWidthProperty().bind(chatsZone.widthProperty());

        chatsZone.getChildren().add(chats); //Cargar el componente de busqueda

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(4);
        shadow.setOffsetY(0);
        shadow.setRadius(5);
        shadow.setColor(Color.web("#000000").deriveColor(0, 1, 1, 0.2));
        historyContainer.setEffect(shadow);
    }

    @FXML
    public void setMessage(ActionEvent act){
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER_RIGHT);

        StackPane message = (StackPane) loader.getComponent("Components/own-message.fxml");
        message.prefWidthProperty().bind(chatsContainer.widthProperty().multiply(0.5));
        ((Label) message.lookup("#messageText")).setMaxWidth(chatsContainer.getWidth() * 0.5);

        container.getChildren().add(message);

        if(getTextMessage() != ""){
            ((Label) message.lookup("#messageText")).setText(getTextMessage());
            ((VBox)chatsContainer.lookup("#chatsBox")).getChildren().add(container);
        }

        sendMessageField.setText("");
    }
    

    public String getTextMessage(){
        return sendMessageField.getText();
    }
}