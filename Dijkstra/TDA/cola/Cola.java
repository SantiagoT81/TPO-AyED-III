package TDA.cola;

public class Cola implements ICola {
    class Nodo {
        int valor;
        Nodo next;
    }

    Nodo head;
    Nodo end;


    public Cola() {
        head = null;
        end = null;
    }

    public void acolar(int pedido) {
        Nodo nodo = new Nodo();
        nodo.valor = pedido;
        nodo.next = null;

        if (end != null)
            end.next = nodo;

        end = nodo;

        if (head == null)
            head = end;
    }

    public void desacolar() {
        head = head.next;
    }

    public boolean colaVacia() {
        return (head == null);
    }

    public int primero() {
        return head.valor;
    }
}