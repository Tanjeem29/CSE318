import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public abstract class Process {
    ArrayList<ArrayList<Variable>> al;
    ValHeu vl;
    VarHeu vr;
    int vrf = 0;
    HashSet<Variable> hs;
    int cnt =0;
    int bt = 0;
    int N;
    public void populate(int[][] a, int n){
        hs = new HashSet<>();
        al = new ArrayList<>();
        N = n;
        Variable temp;
        for(int i = 0 ; i < n ; i++){
            al.add(new ArrayList<>());
            for(int j = 0 ; j < n ; j++){

                temp = new Variable(i,j,N);
                temp.val = a[i][j];

                al.get(i).add(temp);

                if(temp.val == 0){
                    hs.add(temp);
                }
            }
        }
        for(int i = 0; i<N;i++){
            for(int j = 0; j< N ;j++){
                temp = al.get(i).get(j);
                if(temp.val != 0 ) continue;
                for(int k=0;k<N;k++){
                    if(al.get(i).get(k).val != 0){
                        temp.domrem(al.get(i).get(k).val);
                    }
                    else{
                        temp.vah2++;
                    }
                    if(al.get(k).get(j).val != 0){
                        temp.domrem(al.get(k).get(j).val);
                    }
                    else{
                        temp.vah2++;
                    }
                }
                temp.vah2-=2;
            }
        }
    }

    public void setValueHeu(ValHeu VL){
        vl = VL;
    }
    public void setVariableHeu(VarHeu VR){
        vr = VR;
    }

    public void print(){
        for(int i =0 ;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(al.get(i).get(j).val + "\t");
                //System.out.println(al.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    public void printBtNodes(){
        System.out.println("BT:" + bt + "\tNodes: " + cnt);
    }
    public abstract boolean solve();
}

class BackTrack extends Process{

    //int resetVal;

    @Override
    public boolean solve() {


        cnt++;
        //System.out.println(cnt + ": " + hs.size());


        if(hs.isEmpty()) {
            return true;
        }



        Variable temp = vr.findVar(hs);
        //if(temp.dom.isEmpty()) return false;


        boolean res;
        hs.remove(temp);

        if(vrf!= 1 || vrf!=5){
            for(int k=0;k<N;k++){
                if(al.get(k).get(temp.j).val == 0){
                    al.get(k).get(temp.j).vah2--;
                }
                //same row
                if(al.get(temp.i).get(k).val == 0){
                    al.get(temp.i).get(k).vah2--;
                }

            }
            temp.vah2+=2;
        }


        //Collections.sort(temp.dom);
        for(int val : temp.dom){
            ArrayList<Variable> resetArr = new ArrayList<>();
            //resetArr = new ArrayList<>();
            int resetVal = val;
            temp.val = val;


            //change dom/vah1(dom values) and vah2(interrelation)
            //same column
            for(int k=0;k<N;k++){
                if(al.get(k).get(temp.j).val == 0){
                    //al.get(k).get(temp.j).vah2--;
                    if(al.get(k).get(temp.j).dom.contains(val)){
                        resetArr.add(al.get(k).get(temp.j));
                        al.get(k).get(temp.j).domrem(val);
                    }
                }
            //same row
                if(al.get(temp.i).get(k).val == 0){
                    //al.get(temp.i).get(k).vah2--;
                    if(al.get(temp.i).get(k).dom.contains(val)){
                        resetArr.add(al.get(temp.i).get(k));
                        al.get(temp.i).get(k).domrem(val);
                    }
                }

            }




            res = solve();
            if(res) {

                return true;
            }
            else{

                for(Variable v : resetArr){
                    v.dom.add(resetVal);
                    v.vah1++;
                    //hs.add(v);
                }
            }
        }
        temp.val = 0;
        hs.add(temp);
        if(vrf!= 1 || vrf!=5) {
            for (int k = 0; k < N; k++) {
                if (al.get(k).get(temp.j).val == 0) {
                    al.get(k).get(temp.j).vah2++;
                }
                //same row
                if (al.get(temp.i).get(k).val == 0) {
                    al.get(temp.i).get(k).vah2++;
                }

            }
        }

        bt++;
        return false;

    }


}

class ForwardCheck extends Process{

    //int resetVal;

    @Override
    public boolean solve() {


        cnt++;
        //System.out.println(cnt + ": " + hs.size());


        if(hs.isEmpty()) {
            return true;
        }



        Variable temp = vr.findVar(hs);
        //if(temp.dom.isEmpty()) return false;
        //System.out.println(temp);

        boolean res;
        hs.remove(temp);
        if(vrf!= 1 || vrf!=5) {
            for (int k = 0; k < N; k++) {
                if (al.get(k).get(temp.j).val == 0) {
                    al.get(k).get(temp.j).vah2--;
                }
                //same row
                if (al.get(temp.i).get(k).val == 0) {
                    al.get(temp.i).get(k).vah2--;
                }

            }
            temp.vah2 += 2;
        }




        for(int val : temp.dom){


            ArrayList<Variable> resetArr = new ArrayList<>();
            //resetArr = new ArrayList<>();
            int resetVal = val;
            temp.val = val;

            int fl = 0;
            //change dom/vah1(dom values) and vah2(interrelation)
            //same column
            for(int k=0;k<N;k++){
                if(al.get(k).get(temp.j).val == 0){
                    //al.get(k).get(temp.j).vah2--;
                    if(al.get(k).get(temp.j).dom.contains(val)){
                        resetArr.add(al.get(k).get(temp.j));
                        al.get(k).get(temp.j).domrem(val);

                        if(al.get(k).get(temp.j).dom.size() == 0) {
                            fl++;
                            break;
                        }
                    }
                }
                //same row
                if(al.get(temp.i).get(k).val == 0){
                    //al.get(temp.i).get(k).vah2--;
                    if(al.get(temp.i).get(k).dom.contains(val)){
                        resetArr.add(al.get(temp.i).get(k));
                        al.get(temp.i).get(k).domrem(val);

                        if(al.get(temp.i).get(k).dom.size() == 0) {
                            fl++;
                            break;
                        }
                    }
                }

            }



            if(fl==0){
                res = solve();
                if(res) {

                    return true;
                }
                else{
                    for(Variable v : resetArr){
                        v.dom.add(resetVal);
                        v.vah1++;
                        //hs.add(v);
                    }
                }
            }
            else {
                for(Variable v : resetArr){
                    v.dom.add(resetVal);
                    v.vah1++;
                    //hs.add(v);
                }
            }
        }
        temp.val = 0;
        hs.add(temp);
        if(vrf!= 1 || vrf!=5) {
            for (int k = 0; k < N; k++) {
                if (al.get(k).get(temp.j).val == 0) {
                    al.get(k).get(temp.j).vah2++;
                }
                //same row
                if (al.get(temp.i).get(k).val == 0) {
                    al.get(temp.i).get(k).vah2++;
                }

            }
        }

        bt++;
        return false;

    }


}
