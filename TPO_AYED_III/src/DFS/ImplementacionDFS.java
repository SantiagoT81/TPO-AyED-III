package DFS;

public class ImplementacionDFS {
    private GrafoEstatico grafo;
    private int tiempo;
    private int[] d;
    private int[] f;
    private char[] marcas;
    private int[] p;

    public ImplementacionDFS(GrafoEstatico grafo) {
        this.grafo = grafo;
        tiempo = 0;
        d = new int[grafo.cantNodos];
        f = new int[grafo.cantNodos];
        marcas = new char[grafo.cantNodos];
        p = new int[grafo.cantNodos];

        for (int i = 0; i < grafo.cantNodos; i++) {
            marcas[i] = 'B';
        }
    }

    private void DFS_FOREST(GrafoEstatico g) {
        TDACONJUNTO vertices = g.vertices();
        TDACONJUNTO verticesAux = new Conjunto();
        verticesAux.inicializarConjunto();

        while (!vertices.conjuntoVacio()) {
            int v = vertices.elegir();
            marcas[v] = 'B';
            p[v] = 0;
            vertices.sacar(v);
            verticesAux.agregar(v);
        }

        tiempo = 0;
        while (!verticesAux.conjuntoVacio()) {
            int v2 = verticesAux.elegir();
            verticesAux.sacar(v2);
            if (marcas[v2] == 'B') {
                DFS(g, v2);
            }
        }
    }

    private TDACONJUNTO adyacentes(int vertice) {
        TDACONJUNTO adyacentes = new Conjunto();
        adyacentes.inicializarConjunto();

        TDACONJUNTO todosVertices = grafo.vertices();
        while (!todosVertices.conjuntoVacio()) {
            int i = todosVertices.elegir();
            todosVertices.sacar(i);

            if (grafo.existeArista(vertice, i)) {
                adyacentes.agregar(i);
            }
        }
        return adyacentes;
    }


    private void DFS(GrafoEstatico g, int origen) {
        tiempo = tiempo + 1;
        d[origen] = tiempo;
        marcas[origen] = 'G';
        TDACONJUNTO ady = adyacentes(origen);
        System.out.println("Visitando: " + origen);
        while (!ady.conjuntoVacio()) {
            int v = ady.elegir();
            ady.sacar(v);
            if (marcas[v] == 'B') {
                p[v] = origen;
                DFS(g, v);
            }
        }
        marcas[origen] = 'N';
        tiempo = tiempo + 1;
        f[origen] = tiempo;
    }

    public static void main(String[] args) {
        GrafoEstatico grafo = new GrafoEstatico();
        grafo.inicializarGrafo();
        grafo.agregarVertice(0);
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);


        grafo.agregarArista(0,1, 11);
        grafo.agregarArista(1,2, 10);
        grafo.agregarArista(1, 6, 25);
        grafo.agregarArista(3,1, 11);
        grafo.agregarArista(3,5, 11);
        grafo.agregarArista(4,7, 11);
        grafo.agregarArista(5,1, 11);
        grafo.agregarArista(6,5, 11);
        grafo.agregarArista(7,5, 11);


        ImplementacionDFS d = new ImplementacionDFS(grafo);
        d.DFS_FOREST(grafo);
    }
}

