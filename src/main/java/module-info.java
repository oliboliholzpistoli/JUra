module com.jura.jura {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.jura.ui;
    opens com.jura.ui to javafx.fxml;
    exports com.jura.control;
    opens com.jura.control to javafx.fxml;
}