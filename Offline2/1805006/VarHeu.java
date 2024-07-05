import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public interface VarHeu {
    public Variable findVar(HashSet<Variable> hs);
}

class VH1 implements VarHeu{
    @Override
    public Variable findVar(HashSet<Variable> hs) {
        int i = 0;
        //if(hs.isEmpty()) return null;
        Variable ans = new Variable(0,0,0);
        ans.vah1 = 100;
        for(Variable v: hs){
            if(i==0) {
                ans = v;
            }
            else{
                if(v.vah1 < ans.vah1){
                    ans = v;
                }
            }
            i++;
        }
        return ans;
    }
}

class VH2 implements VarHeu{
    @Override
    public Variable findVar(HashSet<Variable> hs) {
        int i = 0;
        //if(hs.isEmpty()) return null;
        Variable ans = new Variable(0,0,0);
        for(Variable v: hs){
            if(i==0) {
                ans = v;
            }
            else{
                if(v.vah2 > ans.vah2){
                    ans = v;
                }
            }
            i++;
        }
        return ans;
    }
}

class VH3 implements VarHeu{
    @Override
    public Variable findVar(HashSet<Variable> hs) {
        int i = 0;
        //if(hs.isEmpty()) return null;
        Variable ans = new Variable(0,0,0);
        for(Variable v: hs){
            if(i==0) {
                ans = v;
            }
            else{
                if((v.vah1 < ans.vah1) ||(v.vah1 == ans.vah1 && v.vah2 > ans.vah2)){
                    ans = v;
                }
            }
            i++;
        }
        return ans;
    }
}
class VH4 implements VarHeu{
    @Override
    public Variable findVar(HashSet<Variable> hs) {
        int i = 0;
        //if(hs.isEmpty()) return null;
        Variable ans = new Variable(0,0,0);
        for(Variable v: hs){
            if(i==0) {
                ans = v;
            }
            else{
                if((float)(v.vah1)/v.vah2 < (float)(ans.vah1) / ans.vah2){
                    ans = v;
                }
            }
            i++;
        }
        return ans;
    }
}

class VH5 implements VarHeu{
    @Override
    public Variable findVar(HashSet<Variable> hs) {
        //if(hs.isEmpty()) return null;
        return hs.iterator().next();
    }
}