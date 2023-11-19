package DFS;

public class GrafoEstatico implements TDAGRAFO {
    static int n = 100;
    int [][] MAdy; //matriz de adyacencia
    int [] etiqs; //vector de índices
    int cantNodos;

    @Override
    public void inicializarGrafo() {
        MAdy = new int[n][n];
        etiqs = new int[n];
        cantNodos = 0;
    }

    @Override
    public void agregarVertice(int v) {
        etiqs[cantNodos] = v;
        for(int i = 0; i <= cantNodos; i++){
            MAdy[cantNodos][i] = 0; //Establece el nuevo vertice con todas sus salidas en 0
            MAdy[i][cantNodos] = 0; //Establece en 0 todas las relaciones de los demás vertices con el nuevo
        }
        cantNodos++;
    }
    private int vert2Indice(int v){ //devuelve el índice del vértice buscado
        int i = cantNodos - 1; //parte desde el final
        while(i >= 0 && etiqs[i] != v){ //va hacia la izquierda
            i--;
        }
        return i; //devuelve el índice que puede ser -1 (si no se encontró) o el índice buscado
    }
    @Override
    public void eliminarVertice(int v) {
        int ind = vert2Indice(v); //se obtiene el índice de el vértice en etiqs
        for(int i = 0; i < cantNodos; i++){ //se reemplazas la columna borrada con la última (cantNodos - 1)
            MAdy[i][ind] = MAdy[i][cantNodos - 1];
            MAdy[ind][i] = MAdy[cantNodos - 1][i];
        }

        etiqs[ind] = etiqs[cantNodos - 1]; //se reemplaza la posición del vértice borrado en el vector con el último elemento
        cantNodos--;
    }

    @Override
    public void agregarArista(int v1, int v2, int p) {
        int o = vert2Indice(v1); //obtener de etiqs el índice del primer vértice
        int d = vert2Indice(v2); //indice en etiqs ddel segundo vértice
        MAdy[o][d] = p; //agregar peso
    }

    @Override
    public void eliminarArista(int v1, int v2) {
        int o = vert2Indice(v1);
        int d = vert2Indice(v2);
        MAdy[o][d] = 0;
    }

    @Override
    public int pesoArista(int v1, int v2) {
        int o = vert2Indice(v1);
        int d = vert2Indice(v2);
        return MAdy[o][d];
    }

    @Override
    public TDACONJUNTO vertices() {
        TDACONJUNTO vert = new Conjunto(); //devolver un conjunto con todos los vértices a partir de etiqs
        vert.inicializarConjunto();
        for(int i = 0; i < cantNodos; i++){
            vert.agregar(etiqs[i]);
        }
        return vert;
    }

    @Override
    public boolean existeArista(int v1, int v2) {
        int o = vert2Indice(v1);
        int d = vert2Indice(v2);
        return (MAdy[o][d] != 0);
    }
}
