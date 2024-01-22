module labs {
    requires javafx.controls;
    requires javafx.fxml;

    opens labs to javafx.fxml;

    exports labs;
}
