import java.util.ArrayList;

public interface ValHeu {
    public int findVal(ArrayList<ArrayList<Variable>> al, Variable v);
}

class VLH1 implements ValHeu{

    @Override
    public int findVal(ArrayList<ArrayList<Variable>> al, Variable v) {
        int mincnt = 3*v.mx+1;
        int cnt = 0;
        int ans = 0;
        if(v.dom.isEmpty()) return 0;
        for(int val : v.dom){
            cnt = 0;
            for(int j = 0; j<v.mx; j++){

                if(al.get(v.i).get(j).dom.contains(val) && al.get(v.i).get(j).val == 0){
                    cnt ++;
                }
            }
            for(int i = 0; i<v.mx; i++){
                if(al.get(i).get(v.j).dom.contains(val) && al.get(i).get(v.j).val == 0){
                    cnt ++;
                }
            }
            if(cnt<mincnt){
                mincnt = cnt;
                ans = val;
            }
            System.out.println(cnt + " " + val + " " + mincnt + " " + ans);
        }
        return ans;
    }
}
