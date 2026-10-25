package Logica;

public class CartaInglesa extends Carta{

    private int valor;

    public CartaInglesa(int valor, Palo figura, String color){
        super(valor,figura,color);
    }

    @Override
    public int compareTo(Carta otraCarta) {
        return      0;
    }
}
