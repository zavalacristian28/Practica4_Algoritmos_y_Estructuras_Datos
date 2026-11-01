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

    public boolean moverBloque(Contenedor origen, Contenedor destino, Nodo<Carta> nodoInicioBloque) {
        if (origen == null || destino == null || nodoInicioBloque == null) {
            return false;
        }

        Carta cartaSuperiorBloque = (Carta) nodoInicioBloque.getInfo();
        Nodo<Carta> nodoDestino = destino.getLista().getInicio();
        if (nodoDestino != null) {

            while (nodoDestino.getSig() != null) {
                nodoDestino = nodoDestino.getSig();
            }
        }

        if (nodoDestino != null) {
            Carta cartaDestino = (Carta) nodoDestino.getInfo();
            if (!cartaSuperiorBloque.getPalo().equals(cartaDestino.getPalo())) {
                return false;
            }

            if (cartaSuperiorBloque.compareTo(cartaDestino) != -1) {
                return false;
            }
        }

        Nodo<Carta> actual = nodoInicioBloque;
        while (actual != null && actual.getSig() != null) {
            Carta c1 = (Carta) actual.getInfo();
            Carta c2 = (Carta) actual.getSig().getInfo();

            if (!c1.getPalo().equals(c2.getPalo()) || (c1.getValor() - c2.getValor() != 1)) {
                return false;
            }
            actual = actual.getSig();
        }

        if (origen.getLista().getInicio() == nodoInicioBloque) {
            origen.getLista().setInicio(null); // El origen queda vacío
        } else {
            Nodo<Carta> prev = origen.getLista().getInicio();
            while (prev != null && prev.getSig() != nodoInicioBloque) {
                prev = prev.getSig();
            }
            if (prev != null) {
                prev.setSig(null);
            }
        }

        if (destino.getLista().getInicio() == null) {
            destino.getLista().setInicio(nodoInicioBloque);
        } else {
            nodoDestino.setSig(nodoInicioBloque);
        }
        return true;
    }
    public boolean esBloqueValido(Nodo<Carta> nodoInicioBloque) {
        if (nodoInicioBloque == null) {
            return false;
        }

        Nodo<Carta> actual = nodoInicioBloque;

        while (actual != null && actual.getSig() != null) {
            Carta c1 = (Carta) actual.getInfo();
            Carta c2 = (Carta) actual.getSig().getInfo();
            if (!c1.getPalo().equals(c2.getPalo())) {
                return false;
            }
            if (c1.getValor() - c2.getValor() != 1) {
                return false;
            }
            actual = actual.getSig();
        }
        return true;
    }
    public boolean hayMovimientosPosibles() {

        boolean hayReservaVacia = false;
        for (Contenedor res : reserva) {
            if (res.getLista().getInicio() == null) {
                hayReservaVacia = true;
                break;
            }
        }
        if (hayReservaVacia) {
            for (Contenedor cont : contenedores) {
                if (cont.getLista().getInicio() != null) {
                    return true;
                }
            }
        }

        for (Contenedor cont : contenedores) {
            Nodo r = cont.getLista().getInicio();
            if (r != null) {
                while (r.getSig() != null) {
                    r = r.getSig();
                }
                Carta cartaExpuesta = (Carta) r.getInfo();

                for (Contenedor funda : fundacion) {
                    if (validarMovAFundacion(funda, cartaExpuesta)) {
                        return true;
                    }
                }
            }
        }

        for (Contenedor res : reserva) {
            Nodo r = res.getLista().getInicio();
            if (r != null) {
                Carta cartaReserva = (Carta) r.getInfo();
                for (Contenedor funda : fundacion) {
                    if (validarMovAFundacion(funda, cartaReserva)) {
                        return true;
                    }
                }
            }
        }

        for (Contenedor res : reserva) {
            Nodo rRes = res.getLista().getInicio();
            if (rRes != null) {
                Carta cartaRes = (Carta) rRes.getInfo();

                for (Contenedor contDestino : contenedores) {
                    Nodo rDest = contDestino.getLista().getInicio();
                    if (rDest == null) {
                        return true;
                    }
                    while (rDest.getSig() != null) {
                        rDest = rDest.getSig();
                    }
                    Carta cartaDestino = (Carta) rDest.getInfo();
                    if (cartaRes.getPalo().equals(cartaDestino.getPalo())
                            && cartaRes.compareTo(cartaDestino) == -1) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < contenedores.size(); i++) {
            Contenedor origen = contenedores.get(i);
            Nodo nodoBloque = origen.getLista().getInicio();

            while (nodoBloque != null) {
                if (esBloqueValido(nodoBloque)) {

                    for (int j = 0; j < contenedores.size(); j++) {
                        if (i == j) continue; // No comparar el contenedor consigo mismo
                        Contenedor destino = contenedores.get(j);
                        Nodo rDest = destino.getLista().getInicio();
                        if (rDest == null) {
                            return true;
                        }
                        while (rDest.getSig() != null) {
                            rDest = rDest.getSig();
                        }
                        Carta cartaDestino = (Carta) rDest.getInfo();
                        Carta cartaArribaBloque = (Carta) nodoBloque.getInfo();
                        if (cartaArribaBloque.getPalo().equals(cartaDestino.getPalo())
                                && cartaArribaBloque.compareTo(cartaDestino) == -1) {
                            return true;
                        }
                    }
                }
                nodoBloque = nodoBloque.getSig();
            }
        }
        return false;
    }

}