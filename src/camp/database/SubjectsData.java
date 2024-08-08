package camp.database;
import camp.model.Student;
import camp.model.Subject;
import java.util.ArrayList;
import java.util.List;
import camp.CampManagementApplication;

public class SubjectsData {
    private static List<Subject> subjects;
    public static List<Subject> getSubjects(){
        return subjects;
    }
    public static void setInitSubjects(){
        subjects = new ArrayList<>();
    }
    public static void setSubjects(List<Subject> subs){
        subjects.addAll(subs);
    }
    public static int getSubjectsSize(){
        return subjects.size();
    }
}

