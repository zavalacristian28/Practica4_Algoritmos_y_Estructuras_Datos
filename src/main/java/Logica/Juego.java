package Logica;

import java.util.ArrayList;

public class Juego {
    private ArrayList<Contenedor> contenedores;
    private ArrayList<Contenedor> reserva;
    private ArrayList<Contenedor> fundacion;
    private Mazo mazo;
    private Contenedor contenedor;

    public Juego(Mazo mazo, Contenedor contenedor) {
        contenedores = new ArrayList<>();
        reserva=new ArrayList<>();
        fundacion=new ArrayList<>();
        this.mazo = mazo;
        this.contenedor = contenedor;
    }

    public ArrayList<Contenedor> getContenedores() {
        return contenedores;
    }
    public ArrayList<Contenedor> getReserva(){
        return reserva;
    }
    public ArrayList<Contenedor> getFundacion(){
        return fundacion;
    }

    public void crear8Contenedores() {
        contenedores.clear();
        reserva.clear();
        fundacion.clear();
        for (int i = 0; i < 4; i++) {
            fundacion.add(new Contenedor(mazo));
        }
        for (int x = 0; x < 8; x++) {
            Contenedor cont = new Contenedor(mazo);
            Contenedor reser=new Contenedor(mazo);
            //para asignar cartas
            for (int y = 0; y < 6; y++) {
                CartaInglesa c = mazo.darCarta();
                cont.getLista().insertarInicio(c);
            }
            if(x<4){
                CartaInglesa c=mazo.darCarta();
                reser.getLista().insertarInicio(c);
            }
            contenedores.add(cont);
            reserva.add(reser);
        }
    }

    public boolean moverCarta(Contenedor contenedor,ArrayList<Contenedor> contenedores,
                           ArrayList<Contenedor> reserva,ArrayList<Contenedor> fundacion){
        if(contenedor.getLista().getInicio()==null){
            return false;
        }
        CartaInglesa carta=(CartaInglesa) contenedor.getLista().getInicio().getInfo();
        //este ciclo verifica que la reserva no tena una carta
        for(int x=0;x<reserva.size();x++){
            if(reserva.get(x).getLista().getInicio()==null){
                CartaInglesa c= (CartaInglesa) contenedor.getLista().eliminaFin();
                reserva.get(x).getLista().insertarInicio(c);
                return true;
            }
        }
        //este ciclo es para la fundacion
        for(int x=0;x<fundacion.size();x++){
            if(validarMovAFundacion(fundacion.get(x),carta)){
                CartaInglesa c= (CartaInglesa) contenedor.getLista().eliminaFin();
                fundacion.get(x).getLista().insertarInicio(c);
                return true;
            }

        }
        return false;
    }

    public boolean validarMovAFundacion(Contenedor fundacionDestino, Carta cartaAMover) {
        Nodo r = fundacionDestino.getLista().getInicio();
        if (r == null) {
            return cartaAMover.getValor() == 14;
        }
        Carta cartaSuperior = (Carta) r.getInfo();
        boolean mismoPalo = cartaAMover.getPalo().equals(cartaSuperior.getPalo());
        boolean esElSiguienteNumero = (cartaAMover.compareTo(cartaSuperior) == 1);
        return mismoPalo && esElSiguienteNumero;
    }
}