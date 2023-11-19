package TDA.grafo;

import TDA.conjunto.IConjunto;

public interface IGrafo {
    void agregarVertice(int v);
    void eliminarVertice(int v);
    void agregarArista(int v1, int v2, int p);
    void eliminarArista(int v1, int v2);
    int pesoArista(int v1, int v2);
    IConjunto vertices();
    boolean existeArista(int v1, int v2);
}