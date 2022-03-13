module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens pl.com.danielsokanski to javafx.fxml;
    exports pl.com.danielsokanski;
    exports pl.com.danielsokanski.controller;
    opens pl.com.danielsokanski.controller to javafx.fxml;
    exports pl.com.danielsokanski.view;
    opens pl.com.danielsokanski.view to javafx.fxml;
    opens pl.com.danielsokanski.model to javafx.fxml;
    exports pl.com.danielsokanski.model;
    exports pl.com.danielsokanski.model.openweathermap.common;
    exports pl.com.danielsokanski.model.openweathermap.direct;
    exports pl.com.danielsokanski.model.openweathermap.forecast;
    exports pl.com.danielsokanski.model.openweathermap.weather;
    exports pl.com.danielsokanski.model.openweathermap.daily;
}