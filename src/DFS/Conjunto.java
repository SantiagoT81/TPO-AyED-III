package DFS;

public class Conjunto implements TDACONJUNTO {
    private int[] a;
    private int cant;
    @Override
    public void inicializarConjunto() {
        a = new int[10];
        cant = 0;
    }
    @Override
    public boolean pertenece(int x) {
        int i = 0;
        while(i < cant && a[i] != x){
            i++;
        }
        return (i < cant);
    }
    @Override
    public void agregar(int x) {
        if(!this.pertenece(x)){
            a[cant] = x;
            cant++;
        }
    }

    @Override
    public int elegir() {
        return a[cant - 1];
    }

    @Override
    public boolean conjuntoVacio() {
        return (cant == 0);
    }

    @Override
    public void sacar(int x) {
        int i = 0;
        while(i < cant && a[i] != x){
            i++;
        }
        if(i < cant){
            a[i] = a[cant - 1];
            cant--;
        }
    }


}
