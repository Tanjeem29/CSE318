import java.sql.Array;
import java.util.*;

public class AStarSolve {
    int dirX[] = new int[4];
    int dirY[] = new int[4];
    HashSet<int[][]> hs2;
    HashSet<node> hs;
    PriorityQueue<node> pq;
    ArrayList<node> v;
    int expandedNodes;
    int exploredNodes;
    public void solve(int hType){
        dirX[0] = 0;
        dirX[1] = 1;
        dirX[2] = 0;
        dirX[3] = -1;
        dirY[0] = 1;
        dirY[1] = 0;
        dirY[2] = -1;
        dirY[3] = 0;
        pq = new PriorityQueue<>(1, new nodeComparator());
        //hs = new HashSet<int[][]>();
        hs = new HashSet<node>();
        v = new ArrayList<node>();
        expandedNodes = 0;
        exploredNodes = 0;

        //input
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        String str;
        node s = new node(k,hType);
        node ans = new node(k,hType);
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                str = in.next();
                if(str.equals("*")){
                    s.arr[i][j] = 0;
                    //s.blankR = i;
                    //s.blankC = j;
                }
                else{
                    s.arr[i][j] = Integer.parseInt(str);
                }
                ans.arr[i][j] = k*i +j +1;
            }
        }
        //int inv = getInvCount(s.arr, s.k);
        //System.out.println("Inv count = " + inv);
        if(!solvable(s.arr, s.k)){
            System.out.println("******** Not solvable ********");
            return;
        }

        System.out.println("******** Solvable ********");

        ans.arr[k-1][k-1] = 0;
        s.setG();
        s.setH();
        //s.print();
        pq.add(s);
        //hs.add(s.arr);
        hs.add(s);
        node curr = null;
        //ans.print();
        int r,c;
        while(!pq.isEmpty()){
            curr = pq.poll();
            expandedNodes++;
            //curr.print();
            int fl = 0;
            for (int i=0; i< k; i++){
                for(int j=0;j<k;j++){
                    if(ans.arr[i][j] != curr.arr[i][j]) {
                        fl++;
                        break;
                    }
                }
            }
            if(fl==0) break;
            for(int i=0;i<k;i++){
                for(int j=0;j<k;j++){
                    if(curr.arr[i][j] == 0){
                        for(int d=0;d<4;d++){
                            r = i + dirY[d];
                            c = j + dirX[d];
                            if(curr.idxOK(r,c)){
                                node n = new node(k, hType);
                                for(int p = 0; p<k; p++){
                                    for(int q = 0; q<k;q++){
                                        n.arr[p][q] = curr.arr[p][q];
                                    }
                                }

                                int temp = n.arr[r][c];
                                n.arr[r][c] = 0;
                                n.arr[i][j] = temp;
                                if(hs.contains(n)){
                                    continue;
                                }
                                else{
                                    n.setParent(curr);
                                    n.setH();
                                    n.setG();

                                    pq.add(n);
                                    hs.add(n);
                                    exploredNodes++;
                                    //n.print();
                                }
                                //n.print();
                            }
                        }
                    }

                }
            }
            //if (expandedNodes == 800000) break;
        }
        node temp = curr;
        while(true){
            v.add(temp);
            if(temp.parent == null)
                break;
            temp = temp.parent;
        }
        String hName = new String();
        if(hType == 0){
            hName = "Hamming Distance Heuristic";
        }
        else if(hType == 1){
            hName = "Manhattan Distance Heuristic";
        }


        System.out.println("Using " + hName);
        System.out.println("Optimal Moves: " + curr.getG());
        System.out.println("Explored " + expandedNodes + " nodes");
        System.out.println("Expanded " + exploredNodes + " nodes");
        int mvcnt = 0;
        while (!v.isEmpty()){
            if(mvcnt!=0){
                System.out.println("Move# " + mvcnt);
            }
            else
            {
                System.out.println("-------------------");
                System.out.println("Initial State:");
            }
            temp = v.remove(v.size()-1);
            temp.print();
            mvcnt++;
        }
        System.out.println("---------------------");

    }

    public int getInvCount(int a[][], int k){
        int r1,c1, r2, c2, ans = 0;
        for(int i = 0; i < k * k; i++){
            for(int j=i+1; j< k * k; j++){
                r1 = i/k;
                c1 = i%k;
                r2 = j/k;
                c2 = j%k;
                if(a[r1][c1] != 0 && a[r2][c2] != 0 && a[r1][c1] > a[r2][c2]){
                    ans++;
                }
            }
        }
        return ans;
    }

    public boolean solvable(int a[][], int k){
        int inv = getInvCount(a,k);
        if(k%2 ==1){
            if(inv%2 == 0){
                return true;
            }
            else{
                return false;
            }
        }
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                if(a[i][j] == 0){
                    if( i % 2 != inv % 2 ){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
}


//3
//        1 2 3
//        4 * 5
//        6 7 8
