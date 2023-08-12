module com.jura.jura {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;

    exports com.jura.ui;
    opens com.jura.ui to javafx.fxml;
    exports com.jura.control;
    opens com.jura.control to javafx.fxml;
}