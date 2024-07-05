import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static HashMap<Integer, Course> courses;
    static HashMap<Integer, Student> students;
    public static void populateCourses(String testcaseName) throws FileNotFoundException {
        Scanner sc = new Scanner( new File("./data/" + testcaseName +  ".crs"));
        courses = new HashMap<>();
        int cid, snum;
        while(sc.hasNextLine()){
            if(!sc.hasNextInt()) break;


            cid = sc.nextInt();
            courses.put(cid, new Course(cid));
            snum = sc.nextInt();
            courses.get(cid).totalStudents = snum;
        }

    }

    public static void populateStudents(String testcaseName) throws FileNotFoundException {
        students = new HashMap<>();
        Scanner sc = new Scanner( new File("./data/" + testcaseName +  ".stu"));
        int sid = 1;

        //sc = new Scanner( new File("yor-f-83.stu"));
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String cids[] = line.split(" ");
            if(cids.length == 0) break;
            Student currS = new Student(sid);
            students.put(sid, currS);

            //System.out.print(sid + ": ");
            for(int i = 0; i < cids.length; i++){
                if(cids[i].equals("")) continue;
                int currCid = Integer.parseInt(cids[i]);
                Course currC = courses.get(currCid);
                //System.out.print(Integer.parseInt(cids[i]) + " ");
                for(Course c : currS.enrolledCourses.values()){
                    if(!currC.adjCourses.contains(c)){
                        currC.addAdjCourse(c);
                    }

                }
                currS.enrolledCourses.put(currCid, courses.get(currCid));

            }

            //System.out.println(currS);
            //currS.printEnrolledCoursesIds();
            sid++;
        }
    }

    public static double calcAvgPen(int pType){
        double avgPenalty = 0;
        for(Student s : students.values()){
            avgPenalty += s.calcPenalty(pType);
        }
        avgPenalty/= students.size();
        return avgPenalty;
    }

    public static int swapColors(int i, int j){
        Course course1 = courses.get(i);
        Course course2 = courses.get(j);
        int color1 = course1.color;
        int color2 = course2.color;
        if(course2.adjColors.containsKey(color1)){
            if( !(course2.adjColors.get(color1)==1 && course2.adjCourses.contains(course1)) )
            return -1;
        }
        if(course1.adjColors.containsKey(color2) ){
            if( !(course1.adjColors.get(color2)==1 && course1.adjCourses.contains(course2)) )
            return -2;
        }
        if(color1 == color2){

            return -3;
        }

        course1.color = color2;
        course2.color = color1;
        for(Course c : course1.adjCourses){
            c.remAdjColor(color1);
            c.addAdjColor(color2);

        }
        for(Course c : course2.adjCourses){
            c.remAdjColor(color2);
            c.addAdjColor(color1);
        }
        return 1;
    }


    public static void pairSwapHeu(int times, int pType){
        for(int k = 0; k <times; k++){
            Random r = new Random();
            int  i = r.nextInt(courses.size());
            i++;
            int  j = r.nextInt(courses.size());
            j++;


            double p1 = calcAvgPen(pType);
            swapColors(i, j);

            double p2 = calcAvgPen(pType);

            if (p1 < p2) {
                swapColors(i, j);

            }
            double p3 = calcAvgPen(pType);
        }


    }
    public static void dfs( Course curr, int color2){
        curr.dfs = 1;
        ArrayList<Course> al = new ArrayList<>(curr.adjCourses);
        for(int i = 0; i< al.size(); i++){
            if(al.get(i).dfs == 0 && al.get(i).color == color2){
                dfs(al.get(i), curr.color);
            }
        }
        curr.dfs = 2;
        return;
    }
    public static void kempeChainHelper(Course curr, int color2, int pType){
        dfs(curr, color2);

        double p1 = calcAvgPen(pType);
        int currcolor = curr.color;
        for(Course c : courses.values()){
            if(c.dfs == 2){
                if(c.color == currcolor){
                    c.replaceColor(color2);
                }
                else{
                    c.replaceColor(currcolor);
                }
            }
        }

        double p2 = calcAvgPen(pType);
        if(p1<p2){
            for(Course c : courses.values()){
                if(c.dfs == 2){
                    if(c.color == currcolor){
                        c.replaceColor(color2);
                    }
                    else{
                        c.replaceColor(currcolor);
                    }
                }
            }
        }
        for(Course c : courses.values()){
            if(c.dfs == 2){
                c.dfs = 0;
            }
        }
        return;

    }
    public static void kempeChainApply(int limit, int pType){
        int cnt = 0;
        Random rand = new Random();
        int icnt = 0;
        while(cnt<limit){
            int pick = rand.nextInt(courses.size());
            pick++;
            int color = -1;
            Course curr = courses.get(pick);
            for(Course c : curr.adjCourses){
                if(c.adjColors.get(curr.color) > 1){
                    color = c.color;
                    break;
                }
            }
            if(color == -1) continue;
            cnt++;
            kempeChainHelper(curr, color, pType);


        }

    }



    public static void main(String[] args) throws FileNotFoundException {
        while (true) {
            int TC;
            Scanner sc = new Scanner(System.in);
            String TCname = null;
            System.out.println("Select Test Case:\n1.car-f-92\n2.car-s-91\n3.kfu-s-93\n4.tre-s-92\n5.yor-f-83");
            TC = sc.nextInt();
            if (TC == 1) {
                TCname = "car-f-92";
            } else if (TC == 2) {
                TCname = "car-s-91";
            } else if (TC == 3) {
                TCname = "kfu-s-93";
            } else if (TC == 4) {
                TCname = "tre-s-92";
            } else if (TC == 5) {
                TCname = "yor-f-83";
            }
            // TCname = "yor-f-83";
            // TCname = "tre-s-92";
            // TCname = "kfu-s-93";
            // TCname = "car-s-91";
            // TCname = "car-f-92";


            populateCourses(TCname);
            populateStudents(TCname);


            double avgPenalty = 0;
            System.out.println("Select Constructive Heuristic:\n1.Largest degree\n2.LargestEnrollment\n3.RandomOrder\n4.DSatur");


            int consHeu = sc.nextInt();

            Comparator<Course> courseComparator;
            if (consHeu == 1) {
                courseComparator = new LargestDegree();
            } else if (consHeu == 2) {
                courseComparator = new LargestEnrollment();
            } else if (consHeu == 3) {
                courseComparator = new RandomOrder();
            } else {
                courseComparator = new DSatur();
            }

            System.out.println("Select Penalty Type\n1.Linear\n2.Exponential");
            int pType = sc.nextInt();

            ArrayList<Course> al = new ArrayList<>(courses.values());
            if(consHeu!=3){
                Collections.sort(al, courseComparator);
            }
            else{
                Collections.shuffle(al);
            }
            int mxColorCount = 0;
            for (int j = 0; j < al.size(); j++) {
                if (consHeu > 3) Collections.sort(al, courseComparator);
                Course c = al.get(j);
                for (int i = 1; ; i++) {
                    if (c.adjColors.containsKey(i)) continue;
                    c.color = i;
                    for (Course c2 : c.adjCourses) {
                        c2.addAdjColor(i);
                    }
                    if (mxColorCount < i) mxColorCount = i;
                    break;
                }
            }
            System.out.println(mxColorCount);

            System.out.println("Initial Penalty: " + calcAvgPen(pType));
            int KempeTimes = 1000;
            kempeChainApply(KempeTimes, pType);
            System.out.println("After Kempe-Chain: " + calcAvgPen(pType));

            int pairSwapTimes = 5000;
            pairSwapHeu(pairSwapTimes, pType);
            System.out.println("After Pair-Swap: " + calcAvgPen(pType));

        }
    }
}
