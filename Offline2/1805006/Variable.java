import java.util.ArrayList;
import java.util.Objects;

public class Variable {
    int vah1, vah2;
    int i,j;
    int mx;
    int val;
    ArrayList<Integer> dom;
    public Variable(int i1, int j1, int m){
        mx = m;
        dom = new ArrayList<>();
        for(int i=1; i <= mx;i++){
            dom.add(i);
        }
        i=i1;
        j=j1;
        vah1 = mx;
        val = 0;
        vah2 = 0;
    }
    public void assign(int x){
        val = x;
        domrem(x);
    }
    public void domrem(int x){
        if(dom.contains(x)){
            dom.remove(Integer.valueOf(x));
            vah1--;
            if(vah1 !=dom.size()) System.out.println("ERRORORORRORO");
        }

    }
    public int getVal(){
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return i == variable.i && j == variable.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "Variable{" +
                "vah1=" + vah1 +
                ", vah2=" + vah2 +
                ", i=" + i +
                ", j=" + j +
                ", mx=" + mx +
                ", val=" + val +
                ", dom=" + dom +
                '}';
    }
}
