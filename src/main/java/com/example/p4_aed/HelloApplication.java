package com.example.p4_aed;

import Logica.Carta;
import Logica.ListaDobleCircular;
import Logica.Mazo;
import Logica.Nodo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ListaDobleCircular<Carta> LDC;
        Pane panel=new Pane();
        panel.setStyle("-fx-background-color: #0B3D2E;");
        Scene scene = new Scene(panel, 1120, 740);
        stage.setTitle("Solitario");
        Fondo fondo=new Fondo();
        fondo.crearContenedores(panel);
        Botones boton=new Botones(panel);
        Mazo mazo=new Mazo();

        LDC=mazo.getLDC();

        Nodo<Carta> actual = LDC.getInicio();
        if (actual != null) {
            int totalCartas = mazo.getLongitud()-4;
            int totalCartas2=totalCartas+4;
            int coordX = 150;
            int coordY = 210;

            for(int x=0;x<6;x++){
                coordX=150;
                for(int y=0;y<8;y++){
                    Carta cartaLogica= actual.getInfo();
                    CartaGrafica cg=new CartaGrafica(cartaLogica);
                    cg.setLayoutX(coordX);
                    cg.setLayoutY(coordY);
                    panel.getChildren().add(cg);
                    actual=actual.getSig();
                    coordX+=120;
                }
                coordY+=40;
            }
            coordY=60;
            coordX=150;
            for(int x=0;x<4;x++){
                Carta cartaLogica= actual.getInfo();
                CartaGrafica cg=new CartaGrafica(cartaLogica);
                cg.setLayoutX(coordX);
                cg.setLayoutY(coordY);
                panel.getChildren().add(cg);
                actual=actual.getSig();
                coordX+=120;
            }
        }
        stage.setScene(scene);
        stage.show();
    }
}
