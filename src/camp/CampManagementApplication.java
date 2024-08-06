

package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.model.SubjectManager;
import camp.model.StudentManager;
import camp.model.ScoreManager;

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
        serviceStore = new ArrayList<>();
    }

    public static List<Subject> getSubjectStore() {
        return subjectStore;
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

                case 2 -> Service.displayScoreView(); // 점수 관리

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

        //과목 가져오기
        Map<String, String> subjects = new HashMap();
        for (Subject subject : subjectStore) {
            subjects.put(subject.getSubjectName(), subject.getSubjectType());
        }
        System.out.println(subjects);

        //필수과목 등록
        ArrayList<String> mandatoryArr = new ArrayList<>();
        System.out.println("3개 이상의 필수 과목을 입력해주세요: ");
        sc.nextLine();

        while (true) {
            String essential = sc.nextLine();
            System.out.println(subjects.get(essential));

            boolean isExist = mandatoryArr.contains(essential);

            // 필수과목 입력
            // essential이 필수과목이 아닌경우
            if (subjects.get(essential) != "MANDATORY") {
                System.out.println("필수과목이 아닙니다.");

                //입력값이 강의 목록에 없는 경우
            } else if (!subjects.containsKey(essential)) {
                System.out.println("입력값이 강의목록에 없습니다. 다시 입력해주세요.");

                //이미 수강신청을 한 경우
            } else if (isExist) {
                System.out.println("이미 수강신청한 과목입니다.");
                mandatoryArr.remove(mandatoryArr.size() - 1);

            } else {
                mandatoryArr.add(essential);
            }


            System.out.println(mandatoryArr);


            if (mandatoryArr.size() >= 5) {
                System.out.println("더이상 신청할 수 없습니다.");
                break;
            }

            if (mandatoryArr.size() == 3) {
                System.out.println("입력을 끝내겠습니까?(exit 입력시 종료) : ");
                sc.nextLine();
                String end = sc.next();
                if (Objects.equals(end, "exit")) {
                    System.out.println("입력을 종료합니다.");
                    break;
                }
            }
        }
        // 선택과목 등록
        ArrayList<String> choiceArr = new ArrayList<>();
        System.out.println("2개 이상의 선택 과목을 입력해주세요: ");
        sc.nextLine();

        while (true) {
            String choice = sc.nextLine();

            boolean isChoiceExist = choiceArr.contains(choice);

            // 선택과목 입력
            // choice가 선택과목이 아닌경우
            if (subjects.get(choice) != "CHOICE") {
                System.out.println("선택과목이 아닙니다.");
                //입력값이 강의 목록에 없는 경우
            } else if (!subjects.containsKey(choice)) {
                System.out.println("입력값이 강의목록에 없습니다. 다시 입력해주세요.");
                //이미 수강신청을 한 경우
            } else if (isChoiceExist) {
                System.out.println("이미 수강신청한 과목입니다.");
                choiceArr.remove(choiceArr.size() - 1);
            } else {
                choiceArr.add(choice);
            }
            if (choiceArr.size() == 4) {
                System.out.println("더이상 신청할 수 없습니다.");
                break;
            }
            System.out.println(choiceArr);

            if (choiceArr.size() == 2) {
                System.out.println("입력을 끝내겠습니까?(exit 입력시 종료) : ");
                sc.nextLine();
                String end = sc.next();
                if (Objects.equals(end, "exit")) {
                    System.out.println("입력을 종료합니다.");
                    break;
                }
            }

        }
        String studentId = sequence(INDEX_TYPE_STUDENT);
        Student student = new Student(studentId, studentName, mandatoryArr, choiceArr); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        studentStore.add(student);
        System.out.println(studentStore.size());


        System.out.println("수강생 등록 성공!\n");

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


