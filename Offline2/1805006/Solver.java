import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        while(true){
            int process;
            int VarSel;
            int N;
            Process p = null;
            Scanner in = new Scanner(System.in);

            System.out.println("Select Solver:\n1. BackTracking\n2. Forward Checking\n0 to exit");
            process = in.nextInt();
            System.out.println("Select Variable Selection Heuristic:\n1. VAH1 := Min domain size\n2. VAH2 := Max-forward-degree\n3. First VAH1, then VAH2 tiebreak\n4. VAH1/VAH2 minimize\n5. Random\n0 to exit");
            VarSel = in.nextInt();
            if(process == 0){
                break;
            }
            else if(process == 1){
                p= new BackTrack();
            }
            else if(process == 2){
                p = new ForwardCheck();
            }

            if(VarSel == 0){
                break;
            }
            if(VarSel == 1){
                p.setVariableHeu(new VH1());
            }
            else if(VarSel == 2){
                p.setVariableHeu(new VH2());
            }
            else if(VarSel == 3){
                p.setVariableHeu(new VH3());
            }
            else if(VarSel == 4){
                p.setVariableHeu(new VH4());
            }
            else if(VarSel == 5){
                p.setVariableHeu(new VH5());
            }

            p.vrf = VarSel;


            System.out.println("Enter main data:");

            N = in.nextInt();
            int init[][] = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    init[i][j] = in.nextInt();
                }
            }

            p.populate(init, N);
            //System.out.println(p.hs.size());

            long start = System.nanoTime();
            p.solve();
            long end = System.nanoTime();
            p.print();
            p.printBtNodes();
            System.out.println("Time taken: " + (end - start)/1000 + " micros\n------------------------------\n----------------------------");



            //Process p = new ForwardCheck();

            //System.out.println(p.hs.size());
            //p.setVariableHeu(new VH1());
            //p.setVariableHeu(new VH2());
            //p.setVariableHeu(new VH3());
            //p.setVariableHeu(new VH4());
            //p.setVariableHeu(new VH5());
            //p.print();
            //System.out.println(p.solve());

        }

    }
}



//10
//        0  0   6   0   0   3   4   0   10  0
//        2  6   4   0   0   0   0   0   9   0
//        0  2   10  0   0   0   0   0   5   9
//        10 1   5   4   2   0   0   0   0   0
//        0  0   0   0   1   9   8   4   0   0
//        0  0   3   2   9   0   0   1   0   0
//        6  0   0   0   0   7   0   10  0   5
//        0  0   0   0   0   8   6   5   0   7
//        1  3   0   6   0   0   5   0   0   2
//        0  5   0   9   6   2   0   0   8   0

//15
//        0 6 8 0 0 0 0 0 13 1 4 10 5 12 0
//        15 11 13 12 0 0 0 2 0 3 0 0 0 0 4
//        0 0 10 13 0 6 1 11 0 0 0 14 2 7 0
//        0 0 0 4 13 0 12 0 11 9 10 1 0 0 7
//        0 0 11 6 1 14 0 10 4 0 12 9 0 0 0
//        0 4 0 0 12 11 7 0 14 0 6 3 0 0 1
//        1 9 12 10 8 13 0 0 0 0 0 11 0 0 6
//        14 13 5 8 0 10 0 0 0 0 7 0 1 9 0
//        0 5 0 0 0 7 6 0 15 10 8 0 0 1 2
//        2 0 4 5 14 0 0 7 6 0 0 15 13 0 0
//        11 0 0 0 0 12 8 9 0 0 2 6 0 13 10
//        0 0 0 0 11 0 10 1 7 6 0 0 12 4 13
//        13 12 0 11 5 0 15 6 0 7 0 0 10 0 0
//        6 0 7 0 0 8 0 12 0 14 13 0 15 0 5
//        10 7 0 0 15 0 4 0 12 5 0 0 6 2 0

//
//9	8	6	5	7	3	4	2	10	1
//        2	6	4	1	5	10	7	8	9	3
//        8	2	10	7	4	1	3	6	5	9
//        10	1	5	4	2	6	9	7	3	8
//        5	7	2	3	1	9	8	4	6	10
//        7	7	3	2	9	5	7	1	7	4
//        6	4	9	3	8	7	2	10	3	5
//        4	10	2	10	3	8	6	5	2	7
//        1	3	8	6	10	4	5	9	7	2
//        3	5	7	9	6	2	10	3	8	3
//4
//        1 2 0 4
//        2 3 4 0
//        0 4 0 2
//        0 1 2 0


//4
//1 0 0 0
//0 0 4 0
//0 3 0 0
//0 0 0 2

//
//9	7	6	8	5	3	4	2	10	1
//        2	6	4	3	10	5	1	7	9	8
//        8	2	10	7	4	1	3	6	5	9
//        10	1	5	4	2	6	9	8	7	3
//        3	10	7	5	1	9	8	4	2	6
//        5	8	3	2	9	4	7	1	6	10
//        6	4	9	1	8	7	2	10	3	5
//        4	9	2	10	3	8	6	5	1	7
//        1	3	8	6	7	10	5	9	4	2
//        7	5	1	9	6	2	10	3	8	4