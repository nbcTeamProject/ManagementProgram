package camp.database;
import camp.model.Student;
import camp.model.Subject;
import java.util.ArrayList;
import java.util.List;
import camp.CampManagementApplication;

public class SubjectsData {
    private List<Subject> subjects;
    public List<Subject> getSubjects(){
        return subjects;
    }
    public void setInitSubjects(){
        this.subjects = new ArrayList<>();
    }
    public void setSubjects(List<Subject> subs){
        for(Subject subject : subs){
            this.subjects.add(subject);
        }
    }
}
