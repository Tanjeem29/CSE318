import java.util.Comparator;
import java.util.Random;

public class Comparators {
}

class LargestDegree implements Comparator<Course>{
    @Override
    public int compare(Course o1, Course o2) {
        return o2.adjCourses.size() - o1.adjCourses.size();
    }
}

class LargestEnrollment implements Comparator<Course>{
    @Override
    public int compare(Course o1, Course o2) {
        return o2.totalStudents - o1.totalStudents;
    }
}
class DSatur implements Comparator<Course>{
    @Override
    public int compare(Course o1, Course o2) {
        if(o1.color == 0 && o2.color != 0) return 1;
        if(o2.color == 0 && o1.color != 0) return -1;
        int diff = o2.adjColors.size() - o1.adjColors.size();
        if(diff!=0)
            return diff;
        //int totc1 = 0, totc2 = 0;
        return o2.uncoloredAdj() - o1.uncoloredAdj();
//        o1.totAdjColors = 0;
//        o2.totAdjColors = 0;
//        for(int c1: o1.adjColors.values()) o1.totAdjColors+=c1;
//        for(int c2: o2.adjColors.values()) o2.totAdjColors+=c2;
//        return (o2.adjCourses.size() - o2.totAdjColors) - (o1.adjCourses.size() - o1.totAdjColors);
    }
}

class RandomOrder implements Comparator<Course>{
    @Override
    public int compare(Course o1, Course o2) {
        Random rand = new Random();
        if(rand.nextInt() % 2 == 0) return 1;
        return -1;
    }
}

