package com.example.p4_aed;

import Logica.Contenedor;
import Logica.Juego;
import Logica.Mazo;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
        Mazo mazo=new Mazo();
        Contenedor contenedor=new Contenedor(mazo);
        Juego juego=new Juego(mazo,contenedor);
        mazo.crearMazo();
        //mazo.mostrarMazo();
        mazo.mezclar();
        //mazo.mostrarMazo();
        juego.crear8Contenedores();
        //juego.mostrarContenedores();
    }
}
