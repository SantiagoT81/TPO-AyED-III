package TDA.colaPrioridad;

public interface IColaPrioridad {
    void acolarPrioridad(int x, int prioridad);
    void desacolar();
    int primero();
    int prioridad();
    boolean colaVacia();
}