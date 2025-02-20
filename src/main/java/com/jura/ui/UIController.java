package com.jura.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UIController {
    @FXML
    TextField recipeNameField;
    @FXML
    TextField recipeDurationField;
    @FXML
    TextField recipeServingsField;

    @FXML
    protected void recipeSubmit() {
        System.out.println("Name: "+ recipeNameField.getCharacters().toString());
        System.out.println("Duration: "+ recipeDurationField.getCharacters().toString());
        System.out.println("Servings: "+ recipeServingsField.getCharacters().toString());
    }
}