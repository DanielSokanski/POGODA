//package pl.com.danielsokanski.component;
//
//import javafx.beans.property.StringProperty;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.layout.Pane;
//import javafx.util.Callback;
//import pl.com.danielsokanski.controller.WeatherComponentController;
//
//import java.io.IOException;
//
//public class WeatherComponent extends Pane {
//    private Node view;
//    private WeatherComponentController controller;
//
//    public WeatherComponent() {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myComponent.fxml"));
//        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
//            @Override
//            public Object call(Class<?> param) {
//                return controller = new WeatherComponentController();
//            }
//        });
//        try {
//            view = (Node) fxmlLoader.load();
//
//        } catch (IOException ex) {
//        }
//        getChildren().add(view);
//    }
//
//    public void setTemp(String str) {
//        controller.textField.setText(str);
//    }
//
//    public String getTemp() {
//        return controller.textField.getText();
//    }
//
//    public StringProperty tempProperty() {
//        return controller.textField.textProperty();
//    }
//
//    public void setWindSpeed(String str) {
//        controller.textField.setText(str);
//    }
//
//    public String getWindSpeed() {
//        return controller.textField.getText();
//    }
//
//    public StringProperty windSpeedProperty() {
//        return controller.textField.textProperty();
//    }
//
//    public void setTemp(String str) {
//        controller.textField.setText(str);
//    }
//
//    public String getTemp() {
//        return controller.textField.getText();
//    }
//
//    public StringProperty tempProperty() {
//        return controller.textField.textProperty();
//    }
//}
