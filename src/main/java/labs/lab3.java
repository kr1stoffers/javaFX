package labs;

import java.util.Arrays;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class lab3 extends Application {
    private BarChart<String, Number> bc;
    private XYChart.Series<String, Number> series1;
    private XYChart.Data<String, Number> data1;
    Timeline timeline;

    private void init(Stage primaryStage) {
        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root));

        VBox chartBox = new VBox();
        chartBox.getChildren().add(createChart());
        root.setCenter(chartBox);

        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(new KeyFrame(new Duration(10), new KeyValue(data1.YValueProperty(), 1700)));

        TextField valueField = new TextField();
        Button updateButton = new Button("Update");
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String value = valueField.getText();
                try {
                    int newValue = Integer.parseInt(value);
                    data1.setYValue(newValue);
                } catch (NumberFormatException e) {
                }
            }
        });

        TextField columnField = new TextField();
        Button columnButton = new Button("Set Columns");
        columnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String columns = columnField.getText();
                try {
                    int numColumns = Integer.parseInt(columns);
                    setNumColumns(numColumns);
                } catch (NumberFormatException e) {
                }
            }
        });

        VBox controlsBox = new VBox();
        controlsBox.getChildren().addAll(valueField, updateButton, columnField, columnButton);
        root.setRight(controlsBox);
    }

    final String[] years = { "2007", "2008", "2009" };
    XYChart.Series<String, Number> series2;
    XYChart.Series<String, Number> series3;

    protected BarChart<String, Number> createChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "$", null));
        bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Advanced Bar Chart");
        xAxis.setLabel("Year");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(years)));
        yAxis.setLabel("Price");

        series1 = new XYChart.Series<String, Number>();
        series1.setName("Data Series 1");
        series2 = new XYChart.Series<String, Number>();
        series2.setName("Data Series 2");
        series3 = new XYChart.Series<String, Number>();
        series3.setName("Data Series 3");

        data1 = new XYChart.Data<String, Number>(years[0], (int) (Math.random() * 5000));
        series1.getData().add(data1);
        series1.getData().add(new XYChart.Data<String, Number>(years[1], (int) (Math.random() * 5000)));
        series1.getData().add(new XYChart.Data<String, Number>(years[2], (int) (Math.random() * 5000)));
        series2.getData().add(new XYChart.Data<String, Number>(years[0], (int) (Math.random() * 5000)));
        series2.getData().add(new XYChart.Data<String, Number>(years[1], (int) (Math.random() * 5000)));
        series2.getData().add(new XYChart.Data<String, Number>(years[2], (int) (Math.random() * 5000)));
        series3.getData().add(new XYChart.Data<String, Number>(years[0], (int) (Math.random() * 5000)));
        series3.getData().add(new XYChart.Data<String, Number>(years[1], (int) (Math.random() * 5000)));
        series3.getData().add(new XYChart.Data<String, Number>(years[2], (int) (Math.random() * 5000)));
        bc.getData().add(series1);
        bc.getData().add(series2);
        bc.getData().add(series3);
        return bc;
    }

    private void setNumColumns(int numColumns) {
        bc.getData().clear();
        for (int i = 0; i < numColumns; i++) {
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            series.setName("Data Series " + (i + 1));
            series.getData().add(new XYChart.Data<String, Number>(years[0], (int) (Math.random() * 5000)));
            series.getData().add(new XYChart.Data<String, Number>(years[1], (int) (Math.random() * 5000)));
            series.getData().add(new XYChart.Data<String, Number>(years[2], (int) (Math.random() * 5000)));
            bc.getData().add(series);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
