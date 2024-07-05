import java.util.Arrays;
import java.util.Comparator;

public class node {
    int hType;
    int[][] arr;
    int k;
    int g, h;
    //int blankR, blankC;
    node parent;
    public node(int kIn, int heuType){
        k = kIn;
        hType = heuType;
        arr = new int[k][k];
    }
    public void setH(){
        if(hType == 0){
            HamDist();
        }
        else if(hType == 1){
            ManDist();
        }
    }
    public void setG(){
        if(parent == null){
            g=0;
        }
        else{
            g = parent.getG() + 1;
        }
    }
    public int getF(){
        return g + h;
    }
    public int getG(){
        return g;
    }

    public void ManDist(){
        int r,c;
        h = 0;
        for(int i=0;i<k;i++) {
            for (int j = 0; j < k; j++) {
                if(arr[i][j] == 0) continue;
                r = (arr[i][j] - 1) / k;
                c = (arr[i][j] - 1) % k;
                h += Math.abs(r - i);
                h += Math.abs(c - j);
            }
        }
    }
    public void HamDist(){
        int r,c;
        h = 0;
        for(int i=0;i<k;i++) {
            for (int j = 0; j < k; j++) {
                if(arr[i][j] == 0) continue;
                r = (arr[i][j] - 1) / k;
                c = (arr[i][j] - 1) % k;
                if( r != i || c != j){
                    h++;
                }
            }
        }
    }

    public void print() {
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                if(arr[i][j] != 0){
                    System.out.print(arr[i][j] + "\t");
                }
                else{
                    System.out.print("*\t");
                }
            }
            System.out.println();
        }

        //System.out.println("g: " + g);
        //System.out.println("h: " + h);
        System.out.println("--------------------");
    }

    public boolean idxOK(int x, int y){
        if(x<k && x>=0 && y<k && y>=0) return true;
        return false;
    }

    public void setParent(node curr) {
        parent = curr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        node node = (node) o;
        return Arrays.deepEquals(arr, node.arr);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(arr);
    }
}


class nodeComparator implements Comparator<node> {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(node n1, node n2) {
        if (n1.getF() > n2.getF())
            return 1;
        else if (n1.getF() < n2.getF())
            return -1;
        return 0;
    }
}

