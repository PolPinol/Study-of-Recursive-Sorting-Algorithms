import java.util.ArrayList;

/**
 * Classe en la qual guardem tot el necessari per a executar el Quick Sort
 */
public class ClassQuick {
    private ArrayList<Atleta> a;
    private int i;
    private int j;

    public ClassQuick(){
        a = new ArrayList<Atleta>();
    }

    public ClassQuick(ArrayList<Atleta> a, int i, int j){
        this.a = a;
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public ArrayList<Atleta> getA() {
        return a;
    }

    public void setA(ArrayList<Atleta> a) {
        this.a = a;
    }

}

