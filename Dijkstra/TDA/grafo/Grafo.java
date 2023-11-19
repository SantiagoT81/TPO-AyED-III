package TDA.grafo;

import TDA.conjunto.Conjunto;
import TDA.conjunto.IConjunto;

public class Grafo implements IGrafo {
    class NodoGrafo {
        int vertice;
        NodoArista arista;
        NodoGrafo sigNodo;
    }

    class NodoArista {
        int etiqueta;
        NodoGrafo nodoDestino;
        NodoArista sigArista;
    }

    NodoGrafo origen;

    public Grafo() {
        origen = null;
    }

    public Grafo(Grafo grafoClone) {
        origen = grafoClone.origen;
    }

    public void agregarVertice(int v) {
        NodoGrafo aux = new NodoGrafo();
        aux.vertice = v;
        aux.arista = null;
        aux.sigNodo = origen;
        origen = aux;
    }

    public void eliminarVertice(int v) {
        if (origen.vertice ==  v)
            origen = origen.sigNodo;

        NodoGrafo aux = origen;

        while (aux != null) {
            this.eliminarAristaNodo(aux, v);

            if (aux.sigNodo != null && aux.sigNodo.vertice == v) {
                aux.sigNodo = aux.sigNodo.sigNodo;
            }
            aux = aux.sigNodo;
        }
    }

    public void agregarArista(int v1, int v2, int peso) {
        NodoGrafo n1 = vert2nodo(v1);
        NodoGrafo n2 = vert2nodo(v2);

        NodoArista nuevaArista = new NodoArista();
        nuevaArista.etiqueta = peso;
        nuevaArista.nodoDestino = n2;

        nuevaArista.sigArista = n1.arista;

        n1.arista = nuevaArista;
    }

    public void eliminarArista(int v1, int v2) {
        NodoGrafo n1 = vert2nodo(v1);
        eliminarAristaNodo(n1, v2);
    }

    public int pesoArista(int v1, int v2) {
        NodoGrafo n1 = vert2nodo(v1);

        NodoArista aux = n1.arista;
        while (aux.nodoDestino.vertice != v2) {
            aux = aux.sigArista;
        }

        return aux.etiqueta;
    }

    public IConjunto vertices() {
        IConjunto c = new Conjunto();

        NodoGrafo aux = origen;
        while (aux != null) {
            c.agregar(aux.vertice);
            aux = aux.sigNodo;
        }
        return c;
    }

    public boolean existeArista(int v1, int v2) {
        NodoGrafo n1 = vert2nodo(v1);

        NodoArista aux = n1.arista;
        while (aux != null && aux.nodoDestino.vertice != v2) {
            aux = aux.sigArista;
        }

        return aux != null;
    }

    private void eliminarAristaNodo(NodoGrafo nodo, int v) {
        NodoArista aux = nodo.arista;

        if (aux != null) {
            if (aux.nodoDestino.vertice == v) // chequeamos el priemro
                nodo.arista = aux.sigArista;
            else {
                while (aux.sigArista != null && aux.sigArista.nodoDestino.vertice != v) {
                    aux = aux.sigArista;
                }

                if (aux.sigArista != null) {
                    aux.sigArista = aux.sigArista.sigArista;
                }
            }
        }
    }

    private NodoGrafo vert2nodo(int v) {
        NodoGrafo aux = origen;
        while (aux != null && aux.vertice != v)
            aux = aux.sigNodo;
        return aux;
    }

    public IConjunto adyacentes(int v) {
        IConjunto adys = new Conjunto();
        IConjunto vertex = vertices();

        while (!vertex.conjuntoVacio()) {
            int v2 = vertex.elegir();
            if (existeArista(v, v2)) {
                adys.agregar(v2);
            }
            vertex.sacar(v2);
        }

        return adys;
    }
}