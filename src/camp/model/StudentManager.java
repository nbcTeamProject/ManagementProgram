package camp.model;
import java.util.List;
import java.util.Scanner;
import camp.CampManagementApplication;

public class StudentManager extends Student{
    private String studentId;
    private String studentName;
    private static Scanner sc = new Scanner(System.in);
    private static List<Student>studentStore = CampManagementApplication.getStudentStore();

    // StudentManager 호출되면 Student 객체 생성됨
    public StudentManager(String seq, String studentName) {
        super(seq, studentName);
        this.studentId   = super.getStudentId();
        this.studentName = super.getStudentName();

    }

    // 사용자에게 수강생 번호 입력받아 Student 객체 찾는 메서드
    // 해당 메서드 사용시 sc.nextLine() 메서드 함께 사용권장
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

    // 수강생
}
