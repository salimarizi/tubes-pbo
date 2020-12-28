package com.tubes.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {
    public ImageView imageView;

    public void initialize(){
        Image image = new Image("file:/../assets/bengkel.png");
        imageView.setImage(image);
    }
}
