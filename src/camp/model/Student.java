
package camp.model;
import camp.model.Score;
import java.util.ArrayList;
import camp.database.SubjectsData;

public class Student {
    private  String studentId;
    private  String studentName;
    private  ArrayList<Subject> mandatoryArr;
    private  ArrayList<Subject> choiceArr;


    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.mandatoryArr = new ArrayList<>();
        this.choiceArr = new ArrayList<>();
    }

    public void addMandatoryArr(Subject subject){
        this.mandatoryArr.add(subject);
    }

    public void addChoiceArr(Subject subject){
        this.choiceArr.add(subject);
    }


    // Getter

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public  ArrayList<Subject> getMandatorySubjects() {
        return mandatoryArr;
    }

    public  ArrayList<Subject> getChoiceSubjects() {
        return choiceArr;
    }

    public String[] getMandatorySubjectsByStr(){
        String[] answer = new String[mandatoryArr.size()];
        int idx = 0;
        for(Subject sub : getMandatorySubjects()){
            answer[idx] = sub.getSubjectName();
            idx++;
        }
        return answer;
    }
    public String[] getChoiceSubjectsByStr(){
        String[] answer = new String[choiceArr.size()];
        int idx = 0;
        for(Subject sub : getChoiceSubjects()){
            answer[idx] = sub.getSubjectName();
            idx++;
        }
        return answer;
    }
    //Setter


}