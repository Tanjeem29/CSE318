import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Student {
    public static int linPenalty[] = {Integer.MAX_VALUE, 8, 6, 4, 2, 0};
    public static int expPenalty[] = {Integer.MAX_VALUE, 16 , 8, 4, 2, 1};
    int id;
    int penalty;
    HashMap<Integer, Course> enrolledCourses;

    public Student(int s){
        id = s;
        enrolledCourses = new HashMap<>();
    }

    public int calcPenalty(int t){
        int p[];
        if(t == 1){
            p = linPenalty;
        }
        else{
            p = expPenalty;
        }
        ArrayList<Course> tempAl = new ArrayList<>(enrolledCourses.values());
        int gap;
        penalty = 0;
        for(int i = 0; i< tempAl.size()-1;i++){
            for(int j = i+1; j<tempAl.size(); j++){
                gap = Math.abs(tempAl.get(i).color - tempAl.get(j).color);
                if(gap<6){
                    penalty += p[gap];
//                    if(gap == 0){
//                        System.out.print(id + " ");
//                        System.out.print(tempAl.get(i) + " ");
//                        System.out.println(tempAl.get(j));
//                        return penalty;
//                    }

                }

            }
        }
        return penalty;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", enrolledCourses=" + enrolledCourses.size() +
                '}';
    }

    public void printEnrolledCoursesIds(){
        for (Course c : enrolledCourses.values()){
            System.out.print(c.id + " ");
        }
        System.out.println();
    }
}
