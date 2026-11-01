package com.example.p4_aed;

import Logica.Carta;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CartaGrafica extends StackPane{
    private Carta carta;
    private ArrayList<CartaGrafica> cartasGraficas;
    private Rectangle cuerpo;
    private Label texto;
    private int coordX;
    private int coordY;
    private Pane panel;
    final double[] offsetX = new double[1];
    final double[] offsetY = new double[1];
    private double posXOrigen;
    private double posYOrigen;

    public CartaGrafica(Carta carta){
        this.carta=carta;
        posXOrigen=this.getLayoutX();
        posYOrigen=this.getLayoutY();
        cartasGraficas=new ArrayList<>();
        cuerpo = new Rectangle(80, 120);
        cuerpo.setFill(Color.WHITE);
        cuerpo.setStroke(Color.BLACK);
        cuerpo.setStrokeWidth(2);
         this.texto = new Label(carta.toString());
        this.texto.setStyle("-fx-font-size: 24px;" + "-fx-font-weight: bold;");
        accionesDeLaCarta();
        this.getChildren().addAll(cuerpo, texto);
    }

    public int getCoordX(){
        return coordX;
    }
    public int getCoordY(){return coordY;}
    public void setCoordX(int coordX){this.coordX=coordX;}
    public void setCoordY(int coordY){this.coordY=coordY;}
    public Carta getCarta(){
        return carta;
    }
    public void setTexto(Label texto){
        this.texto=texto;
    }
    public void setCuerpoCarta(Rectangle r){
       // this.cuerpoCarta=r;
    }
    public Label getTexto(){
        return texto;
    }

    public void accionesDeLaCarta(){
        this.setOnMousePressed(event ->{
            guardarPosicionInicial();
            offsetX[0] = event.getX();
            offsetY[0] = event.getY();

            this.toFront();
            event.consume();
        });
        this.setOnMouseDragged(event ->{
            Pane padre = (Pane) this.getParent();
            if (padre != null) {
                Point2D puntoEnPadre = padre.sceneToLocal(event.getSceneX(), event.getSceneY());
                double nuevaX = puntoEnPadre.getX() - offsetX[0];
                double nuevaY = puntoEnPadre.getY() - offsetY[0];
                this.setLayoutX(nuevaX);
                this.setLayoutY(nuevaY);
            }
            });
        this.setOnMouseReleased(event ->{
            regresarAPosicionInicial();
            event.consume();
        });

    }

    public void guardarPosicionInicial() {
        this.posXOrigen = this.getLayoutX();
        this.posYOrigen = this.getLayoutY();
    }

    public void regresarAPosicionInicial() {
        this.setLayoutX(this.posXOrigen);
        this.setLayoutY(this.posYOrigen);
    }

}
