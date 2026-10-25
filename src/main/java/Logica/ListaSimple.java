package Logica;

public class ListaSimple <T>{
    private Nodo <T> inicio;

    public Nodo<T> getInicio(){
        return inicio;
    }


    public void insertarInicio(T dato){
        Nodo <T> n= new Nodo<>(dato);
        n.setSig(inicio);
        inicio=n;
    }

    public void insertarFin(T dato) {
        Nodo<T> n = new Nodo(dato);
        if (inicio == null) {
            n.setSig(inicio);
            inicio = n;
        } else {
            Nodo<T> r = inicio;
            while (r.getSig() != null) {
                r = r.getSig();
            }
            r.setSig(n);
            n.setSig(null);
        }
    }

    public T eliminaInicio(){
        if(inicio==null){
            System.out.println("Lista vacia");
            return null;
        }else{
            T dato= inicio.getInfo();
            inicio=inicio.getSig();
            return dato;
        }
    }

    public T eliminaFin() {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return null;
        } else {
            if (inicio.getSig() == null) {
                T dato = inicio.getInfo();
                inicio = null;
                return dato;
            } else {
                Nodo<T> r = inicio;
                Nodo<T> a = r;
                while (r.getSig() != null) {
                    a = r;
                    r = r.getSig();
                }
                T dato = r.getInfo();
                a.setSig(null);
                return dato;
            }
        }
    }//fin del metodo

    public String toString(){
        Nodo <T> r= inicio;
        String cad="";
        while(r!=null){
            cad=cad + r.getInfo() + "----" ;
            r=r.getSig();
        }
        return cad;
    }
}
