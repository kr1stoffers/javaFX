package labs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ThreadsGUIController {
    @FXML
    private TextField textA;
    @FXML
    private TextField timeA;
    @FXML
    private TextField textB;
    @FXML
    private TextField timeB;
    @FXML
    private TextArea leftText;
    @FXML
    private TextArea rightText;

    SimpleRunnable threadA = new SimpleRunnable();
    SimpleRunnable threadB = new SimpleRunnable();
    Thread tempThreadA = new Thread(threadA);
    Thread tempThreadB = new Thread(threadB);

    @FXML
    private void handleOnStartA(ActionEvent e) {
        leftText.appendText("StartA\n");
        threadA.setTextArea(leftText);
        if (tempThreadA.getState() == Thread.State.NEW) {
            threadA = new SimpleRunnable(textA.getText(), Integer.parseInt(timeA.getText()), leftText);
            tempThreadA = new Thread(threadA);
            tempThreadA.start();
        }
    }

    @FXML // обрабатываем нажатие на кнопку PauseA, для PauseB аналогичный код
    private void handleOnPauseA(ActionEvent e) {
        Button button = (Button) e.getSource(); // получаем ссылку на нажатую кнопку
        switch (button.getText()) { // делаем выбор действий в зависимости от надписи кнопки
            case "pauseA": // если на кнопке надпись PauseA
                threadA.suspendThread(); // то ставим поток на паузу
                button.setText("resumeA"); // и делаем надпись ResumeA
                break;
            case "resumeA": // если на кнопке надпись ResumeA
                threadA.resumeThread(); // то возобновляем поток
                button.setText("pauseA"); // и делаем надпись PauseA
                break;
        }
    }

    @FXML
    private void handleOnStopA(ActionEvent e) {
        leftText.appendText("StopA\n");
        threadA.stopThread();
        threadA = new SimpleRunnable("A", 500, leftText);
        tempThreadA = new Thread(threadA);
    }

    @FXML
    private void handleOnStartB(ActionEvent e) {
        rightText.appendText("StartB\n");
        threadB.setTextArea(rightText);
        if (tempThreadB.getState() == Thread.State.NEW) {
            threadB = new SimpleRunnable(textB.getText(), Integer.parseInt(timeB.getText()), rightText);
            tempThreadB = new Thread(threadB);
            tempThreadB.start();
        }
    }

    @FXML
    private void handleOnPauseB(ActionEvent e) {
        Button button = (Button) e.getSource();
        switch (button.getText()) {
            case "pauseB":
                threadB.suspendThread();
                button.setText("resumeB");
                break;
            case "resumeB":
                threadB.resumeThread();
                button.setText("pauseB");
                break;
        }
    }

    @FXML
    private void handleOnStopB(ActionEvent e) {
        rightText.appendText("StopB\n");
        threadB.stopThread();
        threadB = new SimpleRunnable("B", 500, rightText);
        tempThreadB = new Thread(threadB);
    }
}
