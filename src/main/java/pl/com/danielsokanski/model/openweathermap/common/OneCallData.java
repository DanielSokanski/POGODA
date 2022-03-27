package pl.com.danielsokanski.model.openweathermap.common;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.List;

public class OneCallData {
    private Label temp;
    private Label windSpeed;
    private Label dt;
    private Image image;




    public OneCallData(Label dt, Label temp, Label windSpeed, Image image) {
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.image = image;
        this.dt = dt;
    }


    public Label getTemp() {
        return temp;
    }

    public void setTemp(Label temp) {
        this.temp = temp;
    }

    public Label getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Label windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Label getDt() {
        return dt;
    }

    public void setDt(Label dt) {
        this.dt = dt;
    }
}
