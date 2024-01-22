package labs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class lab2 extends Application {

    private enum Shape {
        CIRCLE, SQUARE, TRIANGLE
    }

    private Shape currentShape = Shape.CIRCLE;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("lab 2");

        BorderPane root = new BorderPane();

        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.setOnMouseClicked(event -> {
            double xPos = event.getX() - 25;
            double yPos = event.getY() - 25;

            switch (currentShape) {
                case CIRCLE:
                    gc.setFill(Color.RED);
                    gc.fillOval(xPos, yPos, 50, 50);
                    break;
                case SQUARE:
                    gc.setFill(Color.BLUE);
                    gc.fillRect(xPos, yPos, 50, 50);
                    break;
                case TRIANGLE:
                    gc.setFill(Color.GREEN);
                    gc.fillPolygon(new double[] { xPos, xPos + 50, xPos + 25 },
                            new double[] { yPos + 50, yPos + 50, yPos }, 3);
                    break;
            }
        });

        root.setCenter(canvas);

        MenuBar menuBar = new MenuBar();
        Menu shapeMenu = new Menu("Примитивы");

        MenuItem circleItem = new MenuItem("Круг");
        circleItem.setOnAction(event -> {
            currentShape = Shape.CIRCLE;
        });

        MenuItem squareItem = new MenuItem("Квадрат");
        squareItem.setOnAction(event -> {
            currentShape = Shape.SQUARE;
        });

        MenuItem triangleItem = new MenuItem("Треугольник");
        triangleItem.setOnAction(event -> {
            currentShape = Shape.TRIANGLE;
        });

        shapeMenu.getItems().addAll(circleItem, squareItem, triangleItem);
        menuBar.getMenus().add(shapeMenu);

        root.setTop(menuBar);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

}