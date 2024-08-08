package camp.database;
import camp.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentsData {
    private static List<Student> students;

    public static List<Student> getStudents(){
        return students;
    }
    public static void setInitStudents(){
        students = new ArrayList<>();
    }
    public static void addStudent(Student student){
        students.add(student);
    }
    public static int getStudentsSize(){
        return students.size();
    }
}

