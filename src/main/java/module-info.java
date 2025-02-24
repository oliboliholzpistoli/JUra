module com.jura {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires com.h2database;

    exports com.jura.ui;
    opens com.jura.ui to javafx.fxml;
    exports com.jura.control;
    opens com.jura.control to javafx.fxml;
    exports com.jura.data.structures;
}