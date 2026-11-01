package com.example.p4_aed;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Botones {
    private Pane panel;
    private Button botonDeshacer;
    private Button botonPista;

    public Botones(Pane panel){
        this.panel=panel;
        botonDeshacer(panel);
        botonPista(panel);
    }

    public Button crearBoton(String texto,double x,double y){
        Button boton=new Button(texto);
        boton.setPrefWidth(200);
        boton.setPrefHeight(30);
        boton.setStyle("-fx-font-size: 16px;");
        boton.setLayoutX(x);
        boton.setLayoutY(y);
        boton.setStyle("-fx-font-size: 18px;"+"-fx-text-fill: white;"+"-fx-background-color: #145A42;");
        boton.setOnMouseEntered(e -> {
            boton.setStyle("-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-background-color: #D4AF37;");
        });
        //Esta es para que regrese a su base cuando ya no esta situado sobre el
        boton.setOnMouseExited(e -> {
            boton.setStyle("-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-background-color: #145A42;");
        });
        return boton;
    }

    public void botonPista(Pane panel){
        Button botonPista=crearBoton("Pista",400,5);
        panel.getChildren().add(botonPista);
    }

    public void botonDeshacer(Pane panel){
        Button botonDeshacer=crearBoton("Deshacer",150,5);
        panel.getChildren().add(botonDeshacer);
    }
}
