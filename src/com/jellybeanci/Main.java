package com.jellybeanci;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

public class Main extends Application
{

    private static final String title = "Data Visualizer";
    public static final int width = 1250;
    public static final int height = 600;

    //private final ObservableList<Country> countryData = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        root.setOnKeyPressed(e -> {
            switch (e.getCode())
            {
                case F1:
                {
                    System.out.println("Find help");
                    break;
                }
            }
        });
        stage.setTitle(title);
        stage.setMinHeight(750);
        stage.setMinWidth(750);
        stage.setScene(new Scene(root, width - 10, height - 10));
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}