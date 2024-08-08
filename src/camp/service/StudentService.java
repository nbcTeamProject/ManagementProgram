
package camp.service;

import camp.database.StudentsData;
import camp.database.SubjectsData;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.CampManagementApplication;
import java.util.*;
import java.util.Scanner;
import camp.service.SubjectService;



public class StudentService {
    private static Scanner sc = new Scanner(System.in);
    private List<Student> students = CampManagementApplication.getStudentsData().getStudents();




    // 사용자에게 수강생 번호 입력받아 Student 객체 찾는 메서드
    public Student getStudent() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String tempStudentNum = sc.next();
        for (int i = 0; i < students.size(); i++) {
            Student tempStudent = students.get(i);
            if(tempStudent.getStudentId().equals("ST"+tempStudentNum)){
                System.out.println(tempStudent.getStudentName() + " 학생의 데이터를 찾았습니다.");
                return tempStudent;
            }
        }
        System.out.println("해당 학생의 데이터를 찾지못했습니다.");
        return null;
    }

    public Student getStudentById( String studentId ){
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getStudentId().equals(studentId)) {
                System.out.println("해당 Student 객체를 찾았습니다.");
                return student;
            }
        }
        System.out.println("해당 Student 객체를 찾지 못했습니다");
        return null;
    }



    // 수강생 저장
    // subject -> 해쉬맵 <과목이름, 과목 타입>
    public static String saveStudent() {
        SubjectService subjectService = new SubjectService();
        Map<String, String> subjects = new HashMap();
        StudentService studentService = new StudentService();
        for (Subject subject : CampManagementApplication.getSubjectsData().getSubjects()) {
            subjects.put(subject.getSubjectName(), subject.getSubjectType());
        }
        System.out.println(subjects.entrySet());

        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.nextLine();
        Student student = new Student(CampManagementApplication.sequence(CampManagementApplication.getINDEX_TYPE_STUDENT()), studentName);

        //필수과목 등록
        ArrayList<Subject> mandatoryArr = student.getMandatorySubjects();
        Loop1:
        while (true) {
            System.out.println("3개 이상의 필수 과목을 입력해주세요: ");
            System.out.println("현재 등록한 필수 과목 수: "+student.getMandatorySubjects().size());
            String essential = sc.nextLine();
            boolean isExist = false;
            for(Subject element : mandatoryArr){
                if(element.getSubjectName().equals(essential)){
                    isExist = true;
                    break;
                }
            }

            // isExist -> true 이면 되돌아가서 과목 입력 다시 받음: 처리할 프로세스 없음 순위 마지막
            if(!isExist){ //  중복 값이 아닌 과목 입력 받았을 때
                if (!subjects.containsKey(essential)) { // 중복 값 아니면서 과목 목록에 없는 과목 입력 받았을 때
                    System.out.println("해당 과목은 필수 과목이 아닙니다.");}
                else { // 중복 값 아니면서 과목 목록에 있는 과목 입력 받을 때
                    if(subjects.get(essential).equals("MANDATORY")){ // 중복 아닌 필수 과목 입력 받았을 때
                        Subject sub = subjectService.getSubject(essential);
                        student.addMandatoryArr(sub);
                        System.out.println("해당 필수 과목을 추가하였습니다.");
                    }else { // 중복 아닌 선택 과목 입력 받았을 때
                        System.out.println("선택 과목이 아닌 필수 과목을 입력해주세요.");
                    }
                }
            } else { // 중복 값 입력 받았을 때
                System.out.println("이미 수강 신청한 과목입니다.");
            }

            if (mandatoryArr.size() >= 5) {
                System.out.println("더이상 신청할 수 없습니다.");
                break;
            }

            if (mandatoryArr.size() >= 3) {
                System.out.println("입력을 끝내겠습니까?(exit 입력시 종료) : ");
                String end = sc.nextLine();
                if (Objects.equals(end, "exit")) {
                    System.out.println("입력을 종료합니다.");
                    break;
                }
            }
        }
        // 선택과목 등록
        ArrayList<Subject> choiceArr = student.getChoiceSubjects();


        while (true) {
            System.out.println("2개 이상의 선택 과목을 입력해주세요: ");
            System.out.println("현재 등록한 선택 과목 수: "+student.getChoiceSubjects().size());

            String choice = sc.nextLine();
            boolean isChoiceExist = false;

            for(Subject element : choiceArr){
                if(element.getSubjectName().equals(choice)){
                    isChoiceExist = true;
                }
            }

            // 선택과목 입력
            // choice가 선택과목이 아닌경우
            if(!isChoiceExist){ //  중복 값이 아닌 과목 입력 받았을 때
                if (!subjects.containsKey(choice)) { // 중복 값 아니면서 과목 목록에 없는 과목 입력 받았을 때
                    System.out.println("해당 과목은 선택 과목이 아닙니다.");}
                else { // 중복 값 아니면서 과목 목록에 있는 과목 입력 받을 때
                    if(subjects.get(choice).equals("CHOICE")){ // 중복 아닌 필수 과목 입력 받았을 때
                        Subject sub = subjectService.getSubject(choice);
                        student.addChoiceArr(sub);
                        System.out.println("해당 선택 과목을 추가하였습니다.");
                    }else { // 중복 아닌 선택 과목 입력 받았을 때
                        System.out.println("필수 과목이 아닌 선택 과목을 입력해주세요.");
                    }
                }
            } else { // 중복 값 입력 받았을 때
                System.out.println("이미 수강 신청한 과목입니다.");
            }

            if (choiceArr.size() >= 4) {
                System.out.println("더이상 신청할 수 없습니다.");
                break;
            }

            if (choiceArr.size() >= 2) {
                System.out.println("입력을 끝내겠습니까?(exit 입력시 종료) : ");
                String end = sc.nextLine();
                if (Objects.equals(end, "exit")) {
                    System.out.println("입력을 종료합니다.");
                    break;
                }
            }

        }
        CampManagementApplication.getStudentsData().addStudent(student);
        return studentName;
    }
}

