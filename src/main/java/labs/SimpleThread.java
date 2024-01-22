// package labs;

// import javafx.application.Platform;
// import javafx.fxml.FXML;
// import javafx.scene.control.TextArea;

// public class SimpleThread extends Thread {
// private String s;
// private volatile boolean stopped = false;
// public String textA;
// public Integer timeA;

// @FXML
// private TextArea leftText = null;

// public SimpleThread(String text, Integer time) { // конструктор потока
// textA = text;
// timeA = time;
// }

// public SimpleThread() {
// textA = "A";
// timeA = 500;
// }

// public void setTextArea(TextArea tArea) {
// leftText = tArea;
// }

// @Override
// public void run() { // основная функция потока
// if (textA == "") {
// textA = s;
// } else {
// s = textA;
// }
// while (!stopped) {

// System.out.println(s);
// Platform.runLater(new Runnable() {
// @Override
// public void run() {
// leftText.appendText(s + "\n");
// }
// });
// try { // нужно для обработки вероятных ошибок при задержке потока
// if (timeA == null) {
// this.sleep(500); // задерживаем работу потока на 0,5 секунды
// } else {
// this.sleep(timeA);
// }
// } catch (InterruptedException e) { // если возникла ошибка -
// System.out.println(s + " exit"); // печатаем сообщение
// return; // и выходим из потока
// }
// }
// this.interrupt(); // прерываем поток
// }

// public void stopThread() { // метод для остановки потока
// stopped = true;
// }

// }