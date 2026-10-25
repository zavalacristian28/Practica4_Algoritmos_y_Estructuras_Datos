package Logica;

public abstract class Carta implements Comparable<Carta> {
    private int valor;
    private Palo palo;
    private String color;

    public Carta(int valor,Palo palo,String color){
        this.valor=valor;
        this.color=color;
        this.palo=palo;
    }

    public int getValor(){return valor;}
    public Palo getPalo(){return palo;}
    public String getColor(){return color;}
    public void setValor(int valor){this.valor=valor;}
    public void setColor(String color){this.color=color;}

    public String toString() {
        return switch (valor) {
            case 14 -> "A" + palo.getFigura();
            case 11 -> "J"+ palo.getFigura();
            case 12 -> "Q" + palo.getFigura();
            case 13 -> "K" + palo.getFigura();
            default -> ""+ valor + palo.getFigura();
        };
    }

    @Override
    public int compareTo(Carta otraCarta) {
        if (this.valor != otraCarta.getValor()) {
            return Integer.compare(this.valor, otraCarta.getValor());
        }
        return Integer.compare(this.palo.ordinal(), otraCarta.getPalo().ordinal());
    }

}
