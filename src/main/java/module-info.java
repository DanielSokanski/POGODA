module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens pl.com.danielsokanski to javafx.fxml;
    exports pl.com.danielsokanski;
    exports pl.com.danielsokanski.controller;
    opens pl.com.danielsokanski.controller to javafx.fxml;
    exports pl.com.danielsokanski.view;
    opens pl.com.danielsokanski.view to javafx.fxml;
    opens pl.com.danielsokanski.model to javafx.fxml;
    exports pl.com.danielsokanski.model;
}