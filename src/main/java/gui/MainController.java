package gui;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button startButton;
    @FXML
    private Button configButton;
    @FXML
    private AnchorPane anchorPaneMain;
    @FXML
    private ImageView imageView;

    @FXML
    public void configButtonAction(ActionEvent event) throws IOException {
        loadConfigView();

    }

    private void loadConfigView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ConfigView.fxml"));
        AnchorPane anchorPane = loader.load();
        ScrollPane newPane = (ScrollPane) anchorPane.getChildren().get(0);
        VBox buttonsVBox = (VBox) anchorPane.getChildren().get(1);
        AnchorPane newAnchorPane = new AnchorPane();
        newPane.setLayoutX(300);

        AnchorPane mainAnchorPane  = (AnchorPane) Main.scene.getRoot();
        mainAnchorPane.getChildren().set(0, newPane);
        mainAnchorPane.getChildren().set(1, buttonsVBox);
        List<CheckBox> checkBoxList = createCheckBoxesOptions();
        for (CheckBox checkBox : checkBoxList) {
            newAnchorPane.getChildren().add(checkBox);
        }
        newPane.setContent(newAnchorPane);

    }

    private List<CheckBox> createCheckBoxesOptions() {
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        try {
            File[] files = new File("options").getCanonicalFile().listFiles();
            int i = 40;
            for (File file : files) {
                String fileName = file.getName().replaceAll(".txt", "");
                CheckBox checkBox = new CheckBox(fileName);
                checkBox.setText(fileName);
                checkBox.setLayoutX(40);
                checkBox.setLayoutY(i);
                checkBoxes.add(checkBox);
                i = i + 40;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return checkBoxes;
    }

    @FXML
    public void startButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GameManagerView.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = ((Node) event.getTarget()).getScene();
        scene.setRoot(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}