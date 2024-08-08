
package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.database.ScoresData;
import camp.database.StudentsData;
import camp.database.SubjectsData;
import camp.service.ScoreService;
import camp.service.StudentService;
import camp.service.SubjectService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소


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
    private static void setInitData() {
        ScoresData.setInitScores();
        StudentsData.setInitStudents();
        SubjectsData.setInitSubjects();
        SubjectsData.setSubjects(List.of(
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
                )));
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
        String studentName = StudentService.saveStudent();
        System.out.println(studentName);
        System.out.println("저장완료");
        System.out.println();
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : StudentsData.getStudents()) {
            int num = 1;
            System.out.println(num + " 1) 수강생 이름 : " + student.getStudentName() + "\n  2) 수강생 고유번호 : " + student.getStudentId()
                    + "\n  3) 수강 과목 : [필수 - " + Arrays.toString(student.getMandatorySubjectsByStr()) + "]"
                    + " [선택 - " +  Arrays.toString(student.getChoiceSubjectsByStr()) + "]");
            num++;
        }
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
            System.out.println("4. 수강생의 특정 과목 평균 등급 조회");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAverageGradeBySubject(); // 수강생의 특정 과목 평균 등급 조회
                case 5 -> flag = false; // 메인 화면 이동
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
        // 점수 등록 기능 구현
        // 학생 번호 입력 받기
        StudentService studentService = new StudentService();
        SubjectService subjectService = new SubjectService();
        ScoreService scoreService = new ScoreService();
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;

        System.out.println("시험 점수를 등록합니다...");
        Student student = studentService.getStudent();
        if (student != null) { //  Student 객체 찾았을 때
            while (flag1) { // 시험 과목 입력 루프// 과목 입력 받기
                Subject subject = subjectService.getSubject();
                if (subject != null) { // 과목 찾았을 때
                    flag1 = false;
                    while (flag2) { // 시험 회차 입력 루프
                        System.out.print("\n시험 회차를 입력해주세요:");
                        int tempTestNum = sc.nextInt();
                        if (1 <= tempTestNum && tempTestNum <= 10) {
                            Score score = scoreService.getScore(student, subject, tempTestNum);
                            if (score == null) { // 시험 정보 못 찾았을 때 점수 등록 가능
                                flag2 = false;
                                while (flag3) {// 점수 등록 루프
                                    System.out.println("점수 등록이 가능합니다.");
                                    System.out.println("점수를 입력하세요: ");
                                    int tempTestScore = sc.nextInt();
                                    if (0 <= tempTestScore && tempTestScore <= 100) { // 점수 범위 안에 있을 때
                                        Score tempscore = new Score(sequence(INDEX_TYPE_SCORE), tempTestNum, tempTestScore);
                                        scoreService.registScore(student, subject, tempscore, tempTestScore);
                                        ScoresData.addScore(tempscore);
                                        System.out.println("\n점수 등록 성공!");
                                        flag3 = false;
                                    } else {
                                        System.out.println("올바르지 않은 점수입니다.");
                                        System.out.println("다시 입력하세요: ");
                                    }
                                }
                            } else { // 등록된 시험 정보 찾았을 때
                                System.out.println("이미 등록된 시험은 점수 등록이 불가합니다.");
                                System.out.println("회차 입력을 다시 받습니다.");
                            }
                        } else { // 시험 회차 1~10 아닐 때
                            System.out.println("잘못된 회차 입니다.");
                            System.out.println("회차를 다시 입력하세요.");
                        }
                    }
                } else { // 과목 못찾았을 때
                    System.out.println("과목을 다시 입력받습니다.");
                }
            } // while1

        } else {//  Student 객체 못찾았을 때
            System.out.println("학생 번호를 다시 입력받습니다.");
            createScore();
        }
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        StudentService studentService = new StudentService();
        SubjectService subjectService = new SubjectService();
        ScoreService scoreService = new ScoreService();
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        // 기능 구현
        System.out.println("시험 점수를 수정합니다...");
        Student student = studentService.getStudent();
        if (student != null) { //  Student 객체 찾았을 때
            while (flag1) { // 시험 과목 입력 루프
                // 과목 입력 받기
                Subject subject = subjectService.getSubject();
                if (subject != null) { // 과목 찾았을 때
                    flag1 = false;
                    while (flag2) { // 시험 회차 입력 루프
                        System.out.print("\n시험 회차를 입력해주세요:");
                        int tempTestNum = sc.nextInt();
                        if (1 <= tempTestNum && tempTestNum <= 10) {
                            Score score = scoreService.getScore(student, subject, tempTestNum);
                            if (score != null) { // 등록된 시험 정보 찾아야 수정 가능
                                flag2 = false;
                                while (flag3) {// 점수 등록 루프
                                    System.out.println("점수를 수정합니다.");
                                    System.out.println("점수를 입력하세요: ");
                                    int tempTestScore = sc.nextInt();
                                    if (0 <= tempTestScore && tempTestScore <= 100) { // 점수 범위 안에 있을 때
                                        scoreService.registScore(student, subject, score, tempTestScore);
                                        System.out.println("\n점수 수정 성공!");
                                        flag3 = false;
                                    } else {
                                        System.out.println("올바르지 않은 점수입니다.");
                                        System.out.println("다시 입력하세요: ");
                                    }
                                }
                            } else { // 등록된 시험 정보 못찾았을 때
                                System.out.println("해당 데이터를 조회할 수 없습니다.");
                                System.out.println("회차 입력을 다시 받습니다.");
                            }
                        } else { // 시험 회차 1~10 아닐 때
                            System.out.println("잘못된 회차 입니다.");
                            System.out.println("회차를 다시 입력하세요.");
                        }
                    }
                } else { // 과목 못찾았을 때
                    System.out.println("과목을 다시 입력받습니다.");
                }
            } // while1

        } else {//  Student 객체 못찾았을 때
            System.out.println("학생 번호를 다시 입력받습니다.");
            updateRoundScoreBySubject();
        }
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        StudentService studentService = new StudentService();
        SubjectService subjectService = new SubjectService();
        ScoreService scoreService = new ScoreService();
        boolean flag1 = true;
        boolean flag2 = true;
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        Student student = studentService.getStudent();
        if (student != null) { //  Student 객체 찾았을 때
            while (flag1) { // 시험 과목 입력 루프
                // 과목 입력 받기
                Subject subject = subjectService.getSubject();
                if (subject != null) { // 과목 찾았을 때
                    flag1 = false;
                    while (flag2) { // 시험 회차 입력 루프
                        System.out.print("\n시험 회차를 입력해주세요:");
                        int tempTestNum = sc.nextInt();
                        if (1 <= tempTestNum && tempTestNum <= 10) {
                            Score score = scoreService.getScore(student, subject, tempTestNum);
                            if (score != null) { // 등록된 시험 정보 찾아야 조회 가능
                                flag2 = false;
                                System.out.println(student.getStudentName() + " 학생의 점수를 조회합니다.");
                                System.out.println(student.getStudentName() + " 학생의 " + subject.getSubjectName() + " 과목 성적은 " + score.getTestScore() + " 점으로 등급은" + score.getGrade() + " 입니다.");
                                System.out.println("\n등급 조회 성공!");

                            } else { // 등록된 시험 정보 못찾았을 때
                                System.out.println("해당 데이터를 조회할 수 없습니다.");
                                System.out.println("회차 입력을 다시 받습니다.");
                            }
                        } else { // 시험 회차 1~10 아닐 때
                            System.out.println("잘못된 회차 입니다.");
                            System.out.println("회차를 다시 입력하세요.");
                        }
                    }
                } else { // 과목 못찾았을 때
                    System.out.println("과목을 다시 입력받습니다.");
                }
            } // while1

        } else {//  Student 객체 못찾았을 때
            System.out.println("학생 번호를 다시 입력받습니다.");
            updateRoundScoreBySubject();
        }

    }

    private static void inquireAverageGradeBySubject() {
        StudentService studentService = new StudentService();
        SubjectService subjectService = new SubjectService();
        ScoreService scoreService = new ScoreService();
        boolean flag1 = true;
        System.out.println("과목별 평균 등급을 조회합니다...");
        // 기능 구현
        Student student = studentService.getStudent();
        if (student != null) { //  Student 객체 찾았을 때
            while (flag1) { // 시험 과목 입력 루프
                // 과목 입력 받기
                Subject subject = subjectService.getSubject();
                if (subject != null) { // 과목 찾았을 때
                    flag1 = false;
                    char averageGrade = scoreService.makeAverageGrade(student, subject);
                    if (averageGrade != 'z') { // 등록된 점수 하나라도 찾았을 때
                        System.out.println(student.getStudentName() + " 학생의 " + subject.getSubjectName() + " 과목 평균 등급은 " + averageGrade + " 입니다.");

                    } else { // 등록된 점수 못찾았을 때
                        System.out.println("해당 과목에 등록된 점수가 없습니다.");
                        System.out.println("평균 등급 조회 화면으로 돌아갑니다.");
                        inquireAverageGradeBySubject();
                    }

                } else { // 과목 못찾았을 때
                    System.out.println("과목을 다시 입력받습니다.");
                }
            } // while1

        } else {//  Student 객체 못찾았을 때
            System.out.println("학생 번호를 다시 입력받습니다.");
            inquireAverageGradeBySubject();
        }
    }

    //getter
    public static String getINDEX_TYPE_STUDENT() {
        return INDEX_TYPE_STUDENT;
    }

    public static String getINDEX_TYPE_SUBJECT() {
        return INDEX_TYPE_SUBJECT;
    }

    public static String getINDEX_TYPE_SCORE() {
        return INDEX_TYPE_SCORE;
    }

    public static String getSUBJECT_TYPE_MANDATORY() {
        return SUBJECT_TYPE_MANDATORY;
    }

    public static String getSUBJECT_TYPE_CHOICE() {
        return SUBJECT_TYPE_CHOICE;
    }
}

