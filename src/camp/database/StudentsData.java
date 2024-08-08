package camp.database;
import camp.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentsData {
    private  List<Student> students;

    public  List<Student> getStudents(){
        return students;}
    public  void setInitStudents(){
        students = new ArrayList<>();
    }
    public  void addStudent(Student student){
        students.add(student);
    }
    public  int getStudentsSize(){
        return students.size();
    }
}

