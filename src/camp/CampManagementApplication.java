
package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.service.StudnetService;
import java.util.*;

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
    public static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    public static void setInitData() {
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
    }

    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }

    // index 자동 증가
    public static String sequence(String type) {
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
                //case 2 -> displayScoreView(); // 점수 관리
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
   String savedStudent = StudnetService.saveStudent();
        System.out.println(savedStudent);
        System.out.println("저장완료");

    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : studentStore) {
            int num = 1;
            System.out.println(num + " 1) 수강생 이름 : " + student.getStudentName() + "\n  2) 수강생 고유번호 : " + student.getStudentId()
                    + "\n  3) 수강 과목 : [필수 - " + student.getMandatorySubjects() + "]" + " [선택 - " + student.getChoiceSubjects() + "]");
            num++;
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }



        private static void displayScoreView () {
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

        private static String getStudentId () {
            System.out.print("\n관리할 수강생의 번호를 입력하시오...");
            return sc.next();
        }


    /*---------------------------------------------------------------------------------------*/

    // 수강생의 과목별 시험 회차 및 점수 등록
        private static void createScore () {
            String studentId = getStudentId(); // 관리할 수강생 고유 번호
            System.out.println("시험 점수를 등록합니다...");

            // 기능 구현

            /* 위에 있는 과목들 가져오기 */
            List<String> validSubjects = new ArrayList<>();
            for (Subject subject : subjectStore) {
                validSubjects.add(subject.getSubjectName());
            }
            String subjectName;
            /* 위에 있는 과목들 중 제대로 시험과목을 봤는지 */
            while (true) {
                System.out.println("시험과목을 입력하세요: ");
                subjectName = sc.next();
                if (validSubjects.contains(subjectName)) {
                    break;
                } else {
                    System.out.println("시험과목이 항목에 없습니다. 다시 입력해주세요. ");
                }
            }
            /* 시험 회차 입력 */
            int test;
            while (true) {
                System.out.print("시험 1~10회차 중 본인 시험회차를 입력해주세요: ");
                test = sc.nextInt();

                /* 올바른 시험 회차인지 확인하기 */
                if (1 <= test && test <= 10) {
                    break;
                } else {
                    System.out.print("잘못된 회차입니다. 다시 입력해주새요: ");
                }
            }
            /* 시험 점수 입력 */
            int testscore;
            while (true) {
                System.out.print("시험 점수를 입력해주세요: ");
                testscore = sc.nextInt();
                /* 올바른 점수 입력했는지 확인 */
                if (0 <= testscore && testscore <= 100) {
                    break;
                } else {
                    System.out.print("잘못 입력하였습니다. 0~100 사이의 점수를 다시 입력해주세요: ");
                }
            }
            /* 등록하려는 과목의 회차점수가 이미 등록되어있는지 확인하기, 중복하면 등록불가*/
            for (Score score : scoreStore) {
                /* score 객체 학생 ID,과목명,회차가 등록하려는 이미 저장된 것들과 동일한지 확인*/
                /* if 내 조건을 만족하면 등록불가 */
                if (score.getStudentId().equals(studentId) && score.getSubjectName().equals(subjectName) && score.getTest() == test) {
                    System.out.println("등록하려는 과목의 회차 점수가 이미 등록되어 있습니다. 점수가 중복되어 등록될 수 없습니다. ");
                    return;
                }
            }
            /* scoreStore에 넣기위한 score 객체만들기 */
            Score score = new Score(sequence(INDEX_TYPE_SCORE), studentId, subjectName, test, testscore);
            scoreStore.add(score);
            System.out.println("\n점수 등록 성공!");
        }

    /*---------------------------------------------------------------------------------------*/

    // 수강생의 과목별 회차 점수 수정
        private static void updateRoundScoreBySubject () {
            String studentId = getStudentId(); // 관리할 수강생 고유 번호
            // 기능 구현 (수정할 과목 및 회차, 점수)
            System.out.println("시험 점수를 수정합니다...");
            // 기능 구현
            System.out.println("\n점수 수정 성공!");
        }

        // 수강생의 특정 과목 회차별 등급 조회
        private static void inquireRoundGradeBySubject () {
            String studentId = getStudentId(); // 관리할 수강생 고유 번호
            // 기능 구현 (조회할 특정 과목)
            System.out.println("회차별 등급을 조회합니다...");
            // 기능 구현
            System.out.println("\n등급 조회 성공!");
        }

    //getter
    public static String getINDEX_TYPE_STUDENT(){
        return INDEX_TYPE_STUDENT;
    }
    public static String getINDEX_TYPE_SUBJECT(){
        return INDEX_TYPE_SUBJECT;
    }
    public static String getINDEX_TYPE_SCORE(){
        return INDEX_TYPE_SCORE;
    }
    public static String getSUBJECT_TYPE_MANDATORY(){
        return SUBJECT_TYPE_MANDATORY;
    }
    public static String getSUBJECT_TYPE_CHOICE(){
        return SUBJECT_TYPE_CHOICE;
    }

}


