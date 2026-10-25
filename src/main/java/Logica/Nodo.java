package Logica;

public class Nodo <T>{
    private Carta carta;
    private T info;
    private Nodo <T> sig;
    private Nodo <T> ant;

    public Nodo(Carta carta){
        this.carta=carta;
        this.sig=this;
        this.ant=this;
    }

    public Nodo(T info){
        this.info=info;
        this.sig=null;
    }

    public T getInfo(){
        return info;
    }

    public void setInfo(T info){
        this.info=info;
    }

    public Nodo <T> getSig(){
        return sig;
    }
    public void setSig(Nodo<T> sig){
        this.sig=sig;
    }

    public Nodo <T> getAnt() {
        return ant;
    }

    public void setAnt(Nodo<T> ant){
        this.ant=ant;
    }
}

