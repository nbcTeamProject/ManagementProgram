
package camp.service;

import camp.database.StudentsData;
import camp.database.SubjectsData;
import camp.model.Student;
import camp.model.Subject;
import camp.CampManagementApplication;
import java.util.*;
import java.util.Scanner;
import camp.service.SubjectService;



public class StudentService {
    private static Scanner sc = new Scanner(System.in);


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



    // 수강생 저장
    public static String saveStudent() {
        SubjectService subjectService = new SubjectService();
        Map<String, String> subjects = new HashMap();
        for (Subject subject : SubjectsData.getSubjects()) {
            subjects.put(subject.getSubjectName(), subject.getSubjectType());
        }

        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.nextLine();
        Student student = new Student(CampManagementApplication.sequence(CampManagementApplication.getINDEX_TYPE_STUDENT()), studentName);

        //필수과목 등록
        ArrayList<Subject> mandatoryArr = student.getMandatorySubjects();
        System.out.println("3개 이상의 필수 과목을 입력해주세요: ");


        while (true) {
            String essential = sc.nextLine();
            System.out.println(subjects.get(essential));
            boolean isExist = false;
            if(mandatoryArr!= null){
                for(Subject element : mandatoryArr){
                    if(element.getSubjectName().equals(essential)){
                        isExist = true;
                    }
                }
            }

            // 필수과목 입력
            // essential이 필수과목이 아닌경우
            if (!subjects.get(essential).equals( "MANDATORY")) {
                System.out.println("필수과목이 아닙니다.");

                //입력값이 강의 목록에 없는 경우
            } else if (!subjects.containsKey(essential)) {
                System.out.println("입력값이 강의목록에 없습니다. 다시 입력해주세요.");

                //이미 수강신청을 한 경우
            } else if (isExist) {
                System.out.println("이미 수강신청한 과목입니다.");
                mandatoryArr.remove(mandatoryArr.size() - 1);

            } else {
                //mandatoryArr에 요소 추가
                Subject subject = subjectService.getSubject(essential);
                if(subject!=null){
                    student.addMandatoryArr(subject);
                }else{
                    System.out.println("null");
                }
            }

            if (mandatoryArr.size() >= 5) {
                System.out.println("더이상 신청할 수 없습니다.");
                break;
            }

            if (mandatoryArr.size() == 3) {
                System.out.println("입력을 끝내겠습니까?(exit 입력시 종료) : ");
                String end = sc.next();
                if (Objects.equals(end, "exit")) {
                    System.out.println("입력을 종료합니다.");
                    break;
                }
            }


        }
        // 선택과목 등록
        ArrayList<Subject> choiceArr = student.getChoiceSubjects();
        System.out.println("2개 이상의 선택 과목을 입력해주세요: ");
        sc.nextLine();

        while (true) {
            String choice = sc.nextLine();

            boolean isChoiceExist = false;
            if(choiceArr!= null){
                for(Subject element : choiceArr){
                    if(element.getSubjectName().equals(choice)){
                        isChoiceExist = true;
                    }
                }
            }
            // 선택과목 입력
            // choice가 선택과목이 아닌경우

            if (!subjects.get(choice).equals( "CHOICE")) {
                System.out.println("선택과목이 아닙니다.");
                //입력값이 강의 목록에 없는 경우
            } else if (!subjects.containsKey(choice)) {
                System.out.println("입력값이 강의목록에 없습니다. 다시 입력해주세요.");
                //이미 수강신청을 한 경우
            } else if (isChoiceExist) {
                System.out.println("이미 수강신청한 과목입니다.");
                choiceArr.remove(choiceArr.size() - 1);
            } else {
                Subject subject = subjectService.getSubject(choice);
                if (subject != null) {
                    student.addChoiceArr(subject);
                } else {
                    System.out.println("null");
                }
            }
            if (choiceArr.size() == 4) {
                System.out.println("더이상 신청할 수 없습니다.");
                break;
            }

            if (choiceArr.size() == 2) {
                System.out.println("입력을 끝내겠습니까?(exit 입력시 종료) : ");
                String end = sc.nextLine();
                if (Objects.equals(end, "exit")) {
                    System.out.println("입력을 종료합니다.");
                    break;
                }
            }

        }
        StudentsData.addStudent(student);
        return studentName;
    }
}

