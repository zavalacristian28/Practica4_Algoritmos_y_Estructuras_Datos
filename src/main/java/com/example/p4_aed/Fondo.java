package com.example.p4_aed;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Fondo {
        private Rectangle contenedor;
        private ArrayList<Rectangle> contenedoresGraficos=new ArrayList<>();
        private ArrayList<Rectangle> contenedoresReserva=new ArrayList<>();
        private ArrayList<Rectangle> contendoresFundacion=new ArrayList<>();

        public ArrayList<Rectangle> getContenedoresGraficos(){
            return contenedoresGraficos;
        }
        public ArrayList<Rectangle> getContenedoresReserva(){
            return contenedoresReserva;
        }
        public ArrayList<Rectangle> getContendoresFundacion(){
            return contendoresFundacion;
        }

        public void crearContenedores(Pane panel){
            int coordXCont=150;
            int coordYCont=210;
            int coordXReserva=150;
            int coordYReserva=60;
            int coordXFunda=5;
            int coordYFunda=60;
            //para poner las fundaciones
            for(int x=0;x<4;x++){
                Rectangle r=dibujarContenedor(coordXFunda,coordYFunda);
                contendoresFundacion.add(r);
                panel.getChildren().add(r);
                coordYFunda+=150;
            }
            //Para las reservas y contenedores principales
            for(int x=0;x<8;x++){
                Rectangle r=dibujarContenedor(coordXReserva,coordYReserva);
                Rectangle a=dibujarContenedor(coordXCont,coordYCont);
                panel.getChildren().add(r);
                panel.getChildren().add(a);
                contenedoresGraficos.add(a);
                contenedoresReserva.add(r);
                coordXReserva+=120;
                coordXCont+=120;
            }

        }

        public Rectangle dibujarContenedor(double x, double y){
            Rectangle r=new Rectangle(80,120);
            r.setStroke(Color.WHITE);
            r.setStrokeWidth(5);
            r.setFill(Color.web("#0B3D2E"));
            r.setLayoutX(x);
            r.setLayoutY(y);
            return r;
        }
}
