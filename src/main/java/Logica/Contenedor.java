package Logica;

public class Contenedor {
    private ListaDobleCircular<Carta> LDC;
    private ListaSimple<Carta> lista;

    public Contenedor(Mazo mazo){
        this.LDC=mazo.getLDC();
        lista=new ListaSimple<>();
    }

    public ListaSimple<Carta> getLista(){
        return lista;
    }

}
