package labs;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class SimpleRunnable implements Runnable {
    private String s;
    private volatile boolean stopped = false;
    public String textB;
    public Integer timeB;

    private TextArea Text;
    private boolean suspendFlag = false;

    public SimpleRunnable(String text, Integer time, TextArea tArea) {
        textB = text;
        timeB = time;
        Text = tArea;
    }

    public SimpleRunnable() {
        textB = "B";
        timeB = 500;
    }

    public void setTextArea(TextArea tArea) {
        Text = tArea;
    }

    @Override
    public void run() {
        if (textB == "") {
            textB = s;
        } else {
            s = textB;
        }
        while (!stopped) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Text.appendText(s + "\n");
                }
            });
            try {
                if (timeB == null) {
                    Thread.sleep(500);
                } else {
                    Thread.sleep(timeB);
                }
            } catch (InterruptedException e) {
                System.out.println(s + " exit");
                return;
            }
            synchronized (this) {
                while (suspendFlag) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        System.out.println(s + " exit");
                    }
                }
            }
        }
        Thread.currentThread().interrupt();
    }

    synchronized void suspendThread() {
        suspendFlag = true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Text.appendText("Пауза потока " + s + "\n");
            }
        });
    }

    synchronized void resumeThread() {
        suspendFlag = false;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Text.appendText("Возобновление потока " + s + "\n");
            }
        });
        notify();
    }

    public void stopThread() {
        stopped = true;
    }
}
