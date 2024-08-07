
package camp.service;
import java.util.List;
import java.util.Scanner;
import camp.model.Student;
import camp.database.StudentsData;

public class StudentService {
    private Scanner sc = new Scanner(System.in);

    // 사용자에게 수강생 번호 입력받아 Student 객체 찾는 메서드
    public Student getStudent() {
        List<Student> tempStudents = StudentsData.getStudents();
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String tempStudentNum = sc.next();
        for (int i = 0; i < tempStudents.size(); i++) {
            Student tempStudent = tempStudents.get(i);
            if(tempStudent.getStudentId().equals("ST"+tempStudentNum)){
                System.out.println(tempStudent.getStudentName() + " 학생의 데이터를 찾았습니다.");
                return tempStudent;
            }
        }
        System.out.println("해당 학생의 데이터를 찾지못했습니다.");
        return null;
    }

    public Student getStudentById( String studentId ){
        List<Student> tempStudents = StudentsData.getStudents();
        for (int i = 0; i < tempStudents.size(); i++) {
            Student student = tempStudents.get(i);
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
