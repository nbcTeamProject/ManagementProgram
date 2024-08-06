
package camp.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import camp.CampManagementApplication;
import camp.model.Student;

public class StudentService extends Student {
    private String studentId;
    private String studentName;
    private static List<Student>studentStore = CampManagementApplication.getStudentStore();
    private ArrayList<String> mandatoryArr = getMandatorySubjects();
    private  ArrayList<String> choiceArr = getChoiceSubjects();
    private static Scanner sc = new Scanner(System.in);


    // StudentManager 호출되면 Student 객체 생성됨
    public StudentService(String seq, String studentName) {
        super(seq, studentName);
        this.studentName = super.getStudentName();
        this.studentId = super.getStudentId();
    }

    // 사용자에게 수강생 번호 입력받아 Student 객체 찾는 메서드
    public static Student getStudent() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String tempStudentNum = sc.next();

        for (int i = 0; i < studentStore.size(); i++) {
            Student tempStudent = studentStore.get(i);
            if(tempStudent.getStudentId().equals("ST"+tempStudentNum)){
                System.out.println(tempStudent.getStudentName() + " 학생의 데이터를 찾았습니다.");
                return tempStudent;
            }
        }
        System.out.println("해당 Student 객체를 찾지못했습니다.");
        return null;
    }

    public static Student findStudent( String studentId ){
        for (int i = 0; i < studentStore.size(); i++) {
            Student student = studentStore.get(i);
            if (student.getStudentId().equals(studentId)) {
                System.out.println("해당 Student 객체를 찾았습니다.");
                return student;
            }
        }
        System.out.println("해당 Student 객체를 찾지 못했습니다");
        return null;
    }

    // 수강생
}
