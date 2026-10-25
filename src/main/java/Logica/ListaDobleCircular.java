package Logica;

public class ListaDobleCircular <T> {
    private Nodo <T> inicio;
    private Nodo <T> fin;
    public ListaDobleCircular(){

    }

    public void insertaInicio(T dato){
        Nodo <T> n= new Nodo<>(dato);
        n.setInfo(dato);
        if(inicio==null){
            inicio=n;
            fin=n;
            n.setSig(inicio);
            n.setAnt(inicio);
        }else{
            n.setSig(inicio);
            inicio.setAnt(n);
            inicio=n;
            fin.setSig(inicio);
            n.setAnt(fin);
        }
    }

    public void insertaFin(T dato){
        Nodo <T> n= new Nodo<>(dato);
        n.setInfo(dato);
        if(inicio==null){
            inicio=n;
            fin=n;
            n.setSig(inicio);
            n.setAnt(inicio);
        }else{
            n.setSig(inicio);
            inicio.setAnt(n);
            fin.setSig(n);
            n.setAnt(fin);
            fin=n;
        }
    }

    public T eliminarInicio(){
        T dato =null;
        if(inicio==null){
            System.out.println("Lista vacia");
            return null;
        }else{
            dato= (T) inicio.getInfo();
            if(inicio==fin){
                inicio=null;
                fin=null;
            }else{
                fin.setSig(inicio.getSig());
                inicio=inicio.getSig();
                inicio.setAnt(fin);
            }
        }
        return dato;
    }

    public T eliminarFin(){
        T dato=null;
        if(inicio==null){
            System.out.println("Lista vacia");
            return dato;
        }else{
            dato= (T) fin.getInfo();
            if(inicio==fin){
                inicio=null;
                fin=null;
            }else{
                Nodo r=fin.getAnt();
                r.setSig(inicio);
                inicio.setAnt(r);
                fin=r;
            }
        }
        return dato;
    }

    public String recorrerLista(){
        if(inicio == null){
            return "Lista vacia";
        }
        Nodo <T> n=inicio;
        String cad="";
        do{
            cad=cad + n.getInfo() + "\n";
            n = n.getSig();
        }while(n != inicio);
        return cad;
    }

    public Nodo<T> getInicio(){
        return inicio;
    }
    public void vaciar() {
        inicio = null;
        fin = null;
    }
}
