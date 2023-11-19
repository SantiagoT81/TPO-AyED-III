package TDA.conjunto;

public class Conjunto implements IConjunto {
    static class Nodo {
        int info;
        Nodo next;
    }

    Nodo head;

    public Conjunto () {
        head = null;
    }

    public void agregar(int x) {
        if (!this.pertenece(x)) {
            Nodo nuevo = new Nodo();
            nuevo.info = x;
            nuevo.next = head;
            head = nuevo;
        }
    }

    public int elegir() {
        return head.info;
    }

    public boolean conjuntoVacio() {
        return (head == null);
    }

    public void sacar(int x) {
        if (head != null) {
            if (head.info == x) {
                head = head.next;
            } else {
                Nodo aux = head;
                while (aux.next != null && aux.next.info != x) {
                    aux = aux.next;
                }
                if (aux.next != null)
                {
                    aux.next = aux.next.next;
                }
            }
        }
    }

    public boolean pertenece(int x) {
        Nodo aux = head;
        while (aux != null && aux.info != x)
            aux = aux.next;
        return (aux != null);
    }

}
