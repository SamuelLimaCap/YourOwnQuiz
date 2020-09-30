package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {



    @FXML public void SaveButtonAction(ActionEvent event) {
        try {
            ClearOptionsFile();
            saveConfig(event);
            goToMainView(event);

            } catch (Exception e) { e.printStackTrace(); }
        }

    private void saveConfig(ActionEvent event) {
        AnchorPane pane = (AnchorPane) ( (Node)event.getTarget() ).getScene().getRoot();
        ScrollPane scrollPane = (ScrollPane) pane.getChildren().get(0);
        for (Node node : ((AnchorPane) scrollPane.getContent()).getChildren()) {
            if ( (node.getClass()==CheckBox.class) && ( ( (CheckBox) node).isSelected() ) ) {
                    System.out.println(((CheckBox) node).getText());
                    WriteInOptionsFile(((CheckBox) node).getText());
            }
        }
    }

    public void goToMainView(ActionEvent event) throws IOException {
        Scene scene = ( (Node) event.getTarget() ).getScene();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
        scene.setRoot(pane);
    }

    @FXML public void CancelButtonAction(ActionEvent event) throws IOException {
        goToMainView(event);
    }
    public void WriteInOptionsFile(String string) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("options.properties", true));
            int lines = 0;
            bw.write(string);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClearOptionsFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("options.properties"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("options.properties"));
            String line = null;
            while (br.readLine() != null) {
                bw.write(line);
            }
            br.close();
            bw.close();
        }catch(Exception e){ e.printStackTrace(); }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
