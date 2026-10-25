package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private Carta carta;
    private Palo palo;
    private ListaDobleCircular<Carta> LDC;
    private ArrayList<Carta> arreglo;

    public ListaDobleCircular<Carta> getLDC(){
        return LDC;
    }

    public Mazo(){
        LDC=new ListaDobleCircular<>();
        arreglo=new ArrayList<>();
        crearMazo();
        mezclar();
    }

    public void crearMazo(){
        LDC.vaciar();
        for(int x=2;x<=14;x++){
            for(Palo palo: Palo.values()){
                CartaInglesa carta=new CartaInglesa(x,palo, palo.getColor());
                LDC.insertaInicio(carta);
            }
        }
    }

    public void mostrarMazo(){
        Nodo<Carta> r = LDC.getInicio();

        if (r == null) {
            System.out.println("El mazo está vacío");
            return;
        }

        do {
            Carta c = (Carta) r.getInfo();
            System.out.println(c);
            r = r.getSig();
        } while (r != LDC.getInicio());
    }

    public void mezclar() {
        arreglo.clear();
        Nodo<Carta> r = LDC.getInicio();
        if (r != null) {
            do {
                arreglo.add((CartaInglesa) r.getInfo());
                r = r.getSig();
            } while (r != LDC.getInicio());
        }

        Collections.shuffle(arreglo);

        LDC.vaciar();

        for (Carta c : arreglo) {
            LDC.insertaInicio(c);
        }
    }
    public CartaInglesa darCarta(){
        return (CartaInglesa) LDC.eliminarFin();
    }
}
