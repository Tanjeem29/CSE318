import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Course {
    int id;
    int totalStudents;
    int color;
    int dfs;
    //int totAdjColors;   //total colored neighbours
    HashMap<Integer, Integer> adjColors;
    //HashSet<Student> enrolledStudents;
    ArrayList<Course> adjCourses;
    public Course(int i){
        id = i;
        totalStudents = 0;
        adjCourses = new ArrayList<>();
        adjColors = new HashMap<>(); //color,count of adjacent nodes
        color = 0;
        dfs = 0;
    }

    public void addAdjCourse(Course c){
        c.adjCourses.add(this);
        adjCourses.add(c);
    }
    
    public void addAdjColor(int i){
        if(adjColors.containsKey(i)){
            int temp = adjColors.get(i);
            adjColors.remove(i);
            temp++;
            adjColors.put(i,temp);
        }
        else{
            adjColors.put(i, 1);
        }
    }
    public void remAdjColor(int i){     // won't do anything if color not present
        if(adjColors.containsKey(i)){
            int temp = adjColors.get(i);
            adjColors.remove(i);
            temp--;
            if(temp>0){
                adjColors.put(i,temp);
            }
        }
    }

    public void replaceColor(int i){
        int temp = color;
        color = i;
        for(Course c : adjCourses){
            c.remAdjColor(temp);
            c.addAdjColor(i);
        }
    }

    public int uncoloredAdj(){
        int cnt = 0;
        for(Course c : adjCourses){
            if(c.color == 0){
                cnt++;
            }
        }
        return cnt;
    }





    public static void main(String[] args) throws IOException {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(1, 5);
        hm.put(2, 1);
        int i = 3;
        if(hm.containsKey(i)){
            int temp = hm.get(i);
            hm.remove(i);
            temp++;
            hm.put(i,temp);
        }
        else{
            hm.put(i, 1);
        }
        System.out.println(hm.get(i));

        System.out.println(hm.size());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", totalStudents=" + totalStudents +
//                ", adjCourses=" + adjCourses.size() +
//                '}';
        return "Course{" + "id=" + id + ", color=" + color;
    }



}
