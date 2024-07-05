import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int htype;
        Scanner in = new Scanner(System.in);
        AStarSolve solver = new AStarSolve();
        while(true){
            System.out.println("\n\nEnter Heuristic:\n0 : Hamming Distance\n1 : Manhattan Distance \n2 : Exit");
            htype = in.nextInt();
            if(htype == 2){
                break;
            }
            else if(htype>2){
                System.out.println("Invalid Input");
                continue;
            }
            System.out.println("Enter k followed by the initial state:");
            solver.solve(htype);
        }


    }
}

//unsolvable
//3
//8 1 2
//* 4 3
//7 6 5



//3
//        1 2 3
//        4 * 5
//        6 7 8

//3
//        1 2 3
//        4 5 *
//        7 8 6
//3
//1 * 3
//4 2 5
//7 8 6

//3
//        1 8 2
//        * 4 3
//        7 6 5

//3
//1 2 3
//4 * 5
//8 6 7


//moves = 51, man ok, hamming crash
//4
//12 1 10 2
//7 11 4 14
//5 * 9 15
//8 13 6 3

//MOves = 31
//4
//2 1 3 4
//5 6 7 8
//9 10 11 *
//12 13 14 15

//4
//* 1 2 3
//5 6 7 4
//9 10 11 8
//13 14 15 12

//4
//1 3 11 14
//9 10 15 4
//* 5 12 8
//6 13 2 7

//4
//13 2 10 3
//1 12 8 4
//5 * 9 6
//15 14 11 7

//4
//6 13 7 10
//8 9 11 *
//15 2 12 5
//14 3 1 4

//unsolvable
//4
//3 9 1 15
//14 11 4 6
//13 * 10 12
//2 7 8 5