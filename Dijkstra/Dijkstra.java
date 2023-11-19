
import TDA.colaPrioridad.ColaPrioridad;
import TDA.colaPrioridad.IColaPrioridad;
import TDA.conjunto.Conjunto;
import TDA.conjunto.IConjunto;
import TDA.grafo.Grafo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static IConjunto clonarVerticesGrafo(IConjunto vGrafoMain) {
        IConjunto nuevoConjunto = new Conjunto();
        IConjunto aux = new Conjunto();

        while (!vGrafoMain.conjuntoVacio()) {
            int v = vGrafoMain.elegir();
            nuevoConjunto.agregar(v);
            aux.agregar(v);
            vGrafoMain.sacar(v);
        }

        while (!aux.conjuntoVacio()) {
            int v = aux.elegir();
            vGrafoMain.agregar(v);
            aux.sacar(v);
        }

        return nuevoConjunto;
    }

    // precondición: debe existir el nodoSrc
    public static void dijkstra(Grafo grafo, int nodoSrc) {
        Map<Integer, Integer> distancia = new TreeMap<>();
        Map<Integer, Integer> padre = new HashMap<>();

        IConjunto verticesSinSetear = clonarVerticesGrafo(grafo.vertices());
        while (!verticesSinSetear.conjuntoVacio()) {
            int vertice = verticesSinSetear.elegir();

            distancia.put(vertice, Integer.MAX_VALUE);
            padre.put(vertice, null);

            verticesSinSetear.sacar(vertice);
        }

        distancia.put(nodoSrc, 0);
        padre.put(nodoSrc, null);

        //System.out.println(distancia.toString());

        // vértices a los que ya se les encontró una distancia mínima
        IConjunto S = new Conjunto();
        IColaPrioridad Q = new ColaPrioridad();

        Q.acolarPrioridad(nodoSrc, 0);

        while (!Q.colaVacia())
        {
            int v = Q.primero();

            IConjunto auxAdy = clonarVerticesGrafo(grafo.adyacentes(v));
            while (!auxAdy.conjuntoVacio()) {
                int vAdy = auxAdy.elegir();

                if (distancia.get(v) + grafo.pesoArista(v, vAdy) < distancia.get(vAdy)) {
                    distancia.put(vAdy, distancia.get(v) + grafo.pesoArista(v, vAdy));
                    padre.put(vAdy, v);
                    Q.acolarPrioridad(vAdy, distancia.get(vAdy));
                }

                auxAdy.sacar(vAdy);
            }

            Q.desacolar();
        }

        System.out.println(distancia.toString());
        System.out.println(padre.toString());

    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);

        grafo.agregarArista(1, 3, 2);
        grafo.agregarArista(3, 4, 8);
        grafo.agregarArista(1, 4, 5);
        grafo.agregarArista(3, 2, 1);
        grafo.agregarArista(2,4, 1);
        grafo.agregarArista(2, 1, 3);

        dijkstra(grafo, 1);
    }
}