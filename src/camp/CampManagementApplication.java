
package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.model.SubjectManager;
import camp.model.StudentManager;
import camp.model.ScoreManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 * first 브랜치에서 코드 작성
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;
    private static List <Service> serviceStore;


    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "setTestscore";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main (String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
        serviceStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }


    /*---------------------------------------------------------------------------------------*/

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        Subject subject;
        Student student;
        Score score;
        Service service;
        int testscore;
        int test;
        /* 위에 있는 과목들 중 제대로 시험과목을 봤는지 */
        // 기능 구현
        while (true) {
            System.out.println("시험 점수를 등록합니다...");
            student = StudentManager.getStudent(); // 관리할 수강생 고유 번호 입력받기
            sc.nextLine();
            if (student != null) {
                subject = SubjectManager.getSubject(); // 과목 입력받아서 Subject 객체 담기
                if (subject != null){
                    break;
                } else { // subject Null 일 때
                    System.out.println("점수 등록 화면으로 돌아갑니다.");
                }
            } else{ // student Null 일때 되돌아감
                System.out.println("점수 등록 화면으로 돌아갑니다.");
            }
        }
        /* 시험 회차 입력 */
        while (true){
            System.out.println("등록할 시험 회차를 입력하세요: ");
            test = sc.nextInt();
            sc.nextLine();
            if (ScoreManager.IsTestIn(test)){ // 시험 회차 입력받고 1~10회차 사이인지 판단
                System.out.println(test + " 회차 시험을 입력하셨습니다.");
                break;
            } else {
                System.out.println("잘못된 회차를 입력하셨습니다. 다시 입력해주세요: ");
            }
        }
        /* 시험 점수 입력 */
        while (true){
            System.out.println("시험 점수를 입력하세요: ");
            testscore = sc.nextInt();
            sc.nextLine();
            if (ScoreManager.IsTestScoreIn(testscore)){ //시험 점수 입력받고 0~100 점 사이인지 판단
                break;
            }
            else {
                System.out.println("잘못된 시험 점수를 입력하셨습니다. 다시 입력해주세요: ");
            }
        }
        service = Service.findService(student,subject,test);
        if(service != null){// service 객체가 등록 되어있지 않다면 새롭게 등록 가능
            System.out.println("등록하려는 과목의 회차 점수가 이미 등록되어 있습니다. 점수가 중복되어 등록될 수 없습니다. ");
            return;
        }
        /* scoreStore에 넣기위한 score 객체만들기 */
        score = new Score("SC"+test,test,testscore);
        scoreStore.add(score);

        service = new Service(score,subject,student.getStudentId());
        serviceStore.add(service);
        System.out.println("\n점수 등록 성공!");
    }

    /*---------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------*/

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        int testScore;
        int testNum;
        String sub;
        String subId;
        ArrayList<String>subjects = new ArrayList<>();
        for (Subject subject : subjectStore){
            subjects.add(subject.getSubjectName());
        }
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("==================================");
        System.out.println("시험 점수를 수정합니다...");
        Student student = StudentManager.getStudent(); // 관리할 수강생 고유 번호
        sc.nextLine();
        // 기능 구현
        //1. 수정할 과목 선택 -> 해당 과목이 과목 리스트에 있는지 확인해서 있으면 다음, 없으면 updateRoundScoreBySubject 메서드 실행
        //2. 수정할 과목의 회차 선택 -> 해당 학생의 해당 과목 회차가 등록이 되어 있으면 다음, 없으면 updateRoundScoreBySubject 메서드 실행
        //3. 점수 입력받기  -> 점수가 0~100 사이 이면 다음, 아니면 updateRoundScoreBySubject 메서드 실행
        //4. Service 객체 수정
        System.out.println("점수를 수정할 과목을 입력해주세요: ");
        sub = sc.nextLine();
        subId = Service.findSubjectId(sub);

        // 입력 받은 과목이 있는지 확인
        if(subjects.contains(sub)){
            System.out.println(sub+" 과목을 선택하셨습니다.");
            Subject subject = Service.findSubject(subjectStore,sub);

            // 해당 과목의 수정할 회차 입력받아 해당 학생의 해당 과목의 회차가 등록되었는지 확인하여 있다면 다음, 없다면 되돌아감
            System.out.println("점수를 수정할 회차를 입력하세요: ");
            testNum = sc.nextInt();
            sc.nextLine();
            if(Service.IsIn(testNum,1,10)){
                for (int i = 0; i < serviceStore.size(); i++) {
                    Service tempService = serviceStore.get(i);
                    if(tempService.getStudentId().equals(student.getStudentId()) && subject.getSubjectId().equals(subId) && tempService.getTest() == testNum) {
                        System.out.println("해당 데이터를 조회하였습니다.");
                        System.out.println("studentId: "+student.getStudentId());
                        System.out.println("점수를 수정해 주세요: ");
                        testScore = sc.nextInt();
                        sc.nextLine();
                        if (Service.IsIn(testScore, 0, 100)) {
                            tempService.setTestscore(testScore, subject);
                            System.out.println("\n점수 수정 성공!");
                            break;
                        } else {
                            System.out.println("잘못 입력하였습니다. 점수 수정 화면으로 돌아갑니다");
                            updateRoundScoreBySubject();
                        }
                    }else {
                        if(i == serviceStore.size()-1){
                            System.out.println("해당 데이터를 확인할 수 없습니다");
                            System.out.println("점수 수정 화면으로 돌아갑니다.");
                            updateRoundScoreBySubject();
                        }
                    }
                }
            }else{
                System.out.println("잘못된 회차입니다. 점수 수정 화면으로 돌아갑니다.");
                updateRoundScoreBySubject();
            }


        } else {
            System.out.println("해당 과목은 존재하지 않습니다.");
            System.out.println("점수 수정 화면으로 돌아갑니다.");
            updateRoundScoreBySubject();
        }
    }
    /*---------------------------------------------------------------------------------------*/


    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String sub;
        Service service;
        Subject subject;
        int testNum;

        Student student = StudentManager.getStudent(); // 관리할 수강생 고유 번호 입력 받기
        sc.nextLine();
        System.out.println("조회할 과목을 입력해주세요: "); // 조회할 과목 입력받기
        sub = sc.nextLine();
        subject = Service.findSubject(subjectStore,sub);
        if(subject.getSubjectName().equals(sub)){
            // 기능 구현 (조회할 특정 과목)
            System.out.println(subject.getSubjectName()+" 과목을 선택하셨습니다.");
            System.out.println("회차별 등급을 조회합니다...");
            // 기능 구현
            System.out.println("몇 회차 성적을 조회하시겠습니까?: ");
            testNum = sc.nextInt();
            sc.nextLine();
            if(Service.IsIn(testNum,1,10)){
                service = Service.findService(student,subject,testNum);
                System.out.println("service 객체 찾음");
                System.out.println(student.getStudentName() + " 학생의 " + subject.getSubjectName() + " 과목 성적: "+service.getTestscore() + "점 입니다.");
                System.out.println(student.getStudentName() + " 학생의 " + subject.getSubjectName() + " 과목 등급은 "+service.getGrade() + " 입니다.");
                System.out.println("\n등급 조회 성공!");
            } else {
                System.out.println("잘못 입력하였습니다. 점수 조회 화면으로 돌아갑니다");
                inquireRoundGradeBySubject();
            }
        }else {
            System.out.println("해당 과목은 존재하지 않습니다.");
            System.out.println("점수 조회 화면으로 돌아갑니다.");
            inquireRoundGradeBySubject();
        }
    }
    //getter
    public static List<Student> getStudentStore(){
        return studentStore;
    }
    public static List<Subject> getSubjectStore(){
        return subjectStore;
    }
    public static List<Score> getScoreStore(){
        return scoreStore;
    }
    public static List<Service> getServiceStore(){
        return serviceStore;
    }

}
