package gui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.entitites.BoolBody;
import model.entitites.Category;
import model.entitites.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.services.GameLogicService;

import java.io.*;
import java.net.URL;
import java.util.*;

public class GameManagerController implements Initializable {

    @FXML private Button cancelButton;
    @FXML private TextField wordTextField;
    @FXML private TextField answerTextField;
    @FXML private Text correctTimesText;
    @FXML private Text wrongTimesText;
    @FXML private Text categoryText;
    @FXML private Text remainingText;

    private GameLogicService service = new GameLogicService();


    // TODO separar a classe de controller das regras e funções back-ends de jogo.
    private void loadCategories() {
        service.loadCategories();
    }

    private void loadWordsQuiz() {
        service.loadWordsQuiz();
    }

    public void chooseWord() {
        BoolBody<Word> word = service.chooseWord();
        if (word.isBool()) {
            wordTextField.setText(word.getT().getOriginal());
            categoryText.setText(word.getT().getCategory().getName());
        } else {
        }


    }

    public void goToMainView(ActionEvent event) throws IOException {
        Scene scene = ( (Node) event.getTarget() ).getScene();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
        scene.setRoot(pane);
    }

    @FXML public void cancelButtonAction(ActionEvent event) throws IOException {
        goToMainView(event);
    }

    @FXML public void nextButtonAction() {
       if (isCorrectAnswer(service.getCurrentId(), answerTextField.getText().toLowerCase())) {
           setCorrect();
           removeWord(service.getCurrentId());
       } else { setWrong(); }
        chooseWord();
       remainingText.setText(String.valueOf(service.getWordsRemaining()));
       if (service.getWordsRemaining()==0) {
           service = new GameLogicService();
           loadContent();
           chooseWord();
       }

       setTextFieldBlank(answerTextField);
    }

    @FXML public void enterKeyAction(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            nextButtonAction();
            //Seta textfield pra vazio;
            setTextFieldBlank(answerTextField);
        }
    }

    private void setTextFieldBlank(TextField field) {
        field.setText("");
    }


    public boolean isCorrectAnswer(Integer id,  String answer) {
        return service.getWords().get(id).isEqualsAnswer(answer);
    }

    private void setCorrect() {
        service.addCorrectTime();
        correctTimesText.setText(String.valueOf(service.getCorrectTimes()));
    }
    private void setWrong() {
        service.addWrongTime();
        wrongTimesText.setText(String.valueOf(service.getWrongTimes()));
    }



    private void removeWord(int num) {
        service.getWords().remove(num);
    }



    public void loadContent() {
        loadCategories();
        loadWordsQuiz();
        service.getWordsRemaining();
        chooseWord();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadContent();
            chooseWord();
            wordTextField.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
