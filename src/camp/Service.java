package camp;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.model.SubjectManager;
import camp.model.StudentManager;
import camp.model.ScoreManager;
import java.util.List;
import java.util.Scanner;
import camp.CampManagementApplication;

public class Service {

    private String studentId;
    private String studentName;
    private String subjectId;
    private String scoreId;
    private int test;
    private int testScore;
    private char grade;
    private static Scanner sc = new Scanner(System.in);

    private static List<Student>studentStore    = CampManagementApplication.getStudentStore();
    private static List<Score>scoreStore        = CampManagementApplication.getScoreStore();
    private static List<Subject> subjectStore   = CampManagementApplication.getSubjectStore();
    private static List <Service> serviceStore  = CampManagementApplication.getServiceStore();




    public Service(Score score, Subject subject, String studentId) {
        this.studentId      = studentId;
        this.subjectId      = subject.getSubjectId();
        this.scoreId        = score.getScoreId();
        this.test           = score.getTest();
        this.testScore      = score.getTestscore();
        this.grade          = makeGrade(this.testScore,subject);
        System.out.println("Service 객체가 성공적으로 만들어졌습니다.");
        System.out.println("Service 객체의 testScore: " + testScore);
        System.out.println("Service 객체의 grade: "+ grade);
        System.out.println("Service 객체의 subjectId: " + subjectId);
        System.out.println("Service 객체의 studentId: " + studentId);

    }
    //Subject 객체를 담은 리스트와 과목 이름으로 Subject 객체 찾는 메서드
    public static Subject findSubject(List<Subject> subjectStore, String subject){
        Subject answer;
        for (int i = 0; i < subjectStore.size(); i++) {
            Subject sub = subjectStore.get(i);
            if (sub.getSubjectName().equals(subject)) {
                System.out.println("해당 Subject 객체를 찾았습니다.");
                return sub;
            }
        }
        System.out.println("해당 Subject 객체를 찾지 못했습니다.");
        return null;
    }

    // 과목 이름으로 과목 Id 찾는 메서드
    public static String findSubjectId(String subName){
        String subId = "";
        switch(subName){
            case "Java" :
                subId = "SU1";
                break;
            case "객체지향" :
                subId = "SU2";
                break;
            case "Spring" :
                subId = "SU3";
                break;
            case "JPA" :
                subId = "SU4";
                break;
            case "MySQL" :
                subId = "SU5";
                break;
            case "디자인 패턴" :
                subId = "SU6";
                break;
            case "Spring Security" :
                subId = "SU7";
                break;
            case "Redis" :
                subId = "SU8";
                break;
            case "MongoDB" :
                subId = "SU9";
                break;
        }
        return subId;
    }
    // 매개변수로 받은 점수가  minNum~maxNum사이 인지 판별해 true false 반환하는 메서드
    public static boolean IsIn(int num, int minNum, int maxNum){
        boolean answer = true;
        if(num <  minNum || num > maxNum){
            answer = false;
        }
        return answer;
    }
    //점수와 과목 이름을 매개변수로 받아 grade 산정하는 메서드
    public static char makeGrade(int score,Subject subject){
        String subjectType = subject.getSubjectType();
        char grade='z';
        if(subjectType.equals("MANDATORY")){
            if(score>=95){
                grade = 'A';
            } else if (score >= 90) {
                grade = 'B';
            } else if (score >= 80) {
                grade = 'C';
            } else if (score >= 70) {
                grade = 'D';
            } else if (score >= 60) {
                grade = 'F';
            } else {
                grade = 'N';
            }
        }else if(subjectType.equals("CHOICE")){
            if(score>=90){
                grade = 'A';
            } else if (score >= 80) {
                grade = 'B';
            } else if (score >= 70) {
                grade = 'C';
            } else if (score >= 60) {
                grade = 'D';
            } else if (score >= 50) {
                grade = 'F';
            } else {
                grade = 'N';
            }
        }
        return grade;
    }
    // service 리스트와, 시험 회차, Student 객체, Subject 객체 입력 받아서 service 객체 반환하는 메서드
    public static Service findService(Student student, Subject subject, int test ){
        for (int i = 0; i < serviceStore.size(); i++) {
            Service tempService = serviceStore.get(i);
            if (tempService.getTest() == test && tempService.getSubjectId().equals(subject.getSubjectId())&&tempService.getStudentId().equals(student.getStudentId())) {
                System.out.println("해당 Service 객체를 찾았습니다.");
                return tempService;
            }
        }
        System.out.println("해당 Service 객체를 찾지 못했습니다.");
        return null;
    }
    // student 리스트와 studentId 받아서 Student 객체 반환하는 메서드
    public static Student findStudent(List<Student>students, String studentId ){
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

    //gettet
    public String getStudentId() {
        return studentId;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public String getScoreId() {return scoreId;}
    public int getTest() { return test;}
    public int getTestscore() { return testScore; }
    public char getGrade() {return grade;}


    //setter

    // Student, Subject, Score 객체 받아서 점수 수정하는 메서드
    public void setTestscore(Student student,Subject subject, Score score){
        System.out.println(student.getStudentName()+" 학생의 점수를 수정합니다.");
        System.out.println("점수를 입력하세요: ");
        int testSc = sc.nextInt();
        if(ScoreManager.IsTestScoreIn(testSc)){
            this.testScore = testSc;
            this.grade = makeGrade(testSc,subject);
            System.out.println(student.getStudentName()+" 학생의 " +subject.getSubjectName()+" 과목 점수가 "+this.testScore+" 점으로 수정되었습니다.");

        }else{
            System.out.println("잘못된 점수를 입력하였습니다. 점수 조회 화면으로 돌아갑니다.");
            updateRoundScoreBySubject();
        }
    }
    /*---------------------------------------------------------------------------------------*/
    // 수강생의 과목별 시험 회차 및 점수 등록 기능
    static void createScore() {
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
    // 수강생의 과목별 회차 점수 수정
    static void updateRoundScoreBySubject() {
        Score score;
        Subject subject;
        Student student;
        Service service;
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("==================================");
        System.out.println("시험 점수를 수정합니다...");
        student = StudentManager.getStudent(); // 관리할 수강생 고유 번호 입력받고 해당 Student 객체 반환 받기
        if(student != null){
            subject = SubjectManager.getSubject();
            // 입력 받은 과목이 있는지 확인
            if(subject != null){ // subject 객체 찾았을 때
                System.out.println(subject.getSubjectName()+" 과목을 선택하셨습니다.");
                // 해당 과목의 수정할 회차 입력받아 해당 학생의 해당 과목의 회차가 등록되었는지 확인하여 있다면 다음, 없다면 되돌아감
                score = ScoreManager.getScore();
                if(score != null){ // 등록된 score 객체 찾았을 때
                    service = Service.findService(student,subject,score.getTest());
                    if(service != null){// 페어링 된 Service 객체 찾았을 때
                        service.setTestscore(student,subject,score);
                    }
                    else { // Service 객체 찾지 못했을 때
                        System.out.println("해당 데이터를 확인할 수 없습니다");
                        System.out.println("점수 수정 화면으로 돌아갑니다.");
                        updateRoundScoreBySubject();
                    }
                }else{ //등록된 score 객체 못찾았을 때
                    System.out.println("점수 수정 화면으로 돌아갑니다.");
                    updateRoundScoreBySubject();
                }
            } else {// subject 객체 못찾았을 때
                System.out.println("등록되지 않은 과목입니다. 다시 입력하세요: ");

            }
        } else { // Student 객체 못찾았을 때
            System.out.println("점수 수정 화면으로 돌아갑니다.");
            updateRoundScoreBySubject();

        }
    }
    static void inquireRoundGradeBySubject() {
        String sub;
        Service service;
        Subject subject;
        Student student;
        Score score;
        int testNum;

        student = StudentManager.getStudent(); // 관리할 수강생 고유 번호 입력 받기
        sc.nextLine();
        if(student != null){ // Student 객체 찾았을 때
            subject = SubjectManager.getSubject(); // 과목 입력 받고 Subject 객체 반환 받음
            if(subject != null){ // Subject 객체 존재할 때
                // 기능 구현 (조회할 특정 과목)
                System.out.println(subject.getSubjectName()+" 과목을 선택하셨습니다.");
                System.out.println("회차별 등급을 조회합니다...");
                // 기능 구현
                score = ScoreManager.getScore();
                if(score != null) { // 등록된 score 객체 찾았을 때{
                    service = Service.findService(student,subject,score.getTest());
                    System.out.println(student.getStudentName() + " 학생의 " + subject.getSubjectName() + " 과목 성적: "+service.getTestscore() + "점 입니다.");
                    System.out.println(student.getStudentName() + " 학생의 " + subject.getSubjectName() + " 과목 등급은 "+service.getGrade() + " 입니다.");
                    System.out.println("\n등급 조회 성공!");
                } else {// 등록된 score 객체 못찾았을 때
                    System.out.println("잘못 입력하였습니다. 점수 조회 화면으로 돌아갑니다");
                    inquireRoundGradeBySubject();
                }
            } else { // Subject 객체 못찾았을 때
                System.out.println("점수 조회 화면으로 돌아갑니다.");
                inquireRoundGradeBySubject();            }

        } else{ // Student 객체 못 찾앗을때
            System.out.println("점수 조회 화면으로 돌아갑니다.");
            inquireRoundGradeBySubject();
        }
    }
    /*---------------------------------------------------------------------------------------*/
    // 점수 기능 전체 화면 메서드
    static void displayScoreView() {
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
                case 1 -> Service.createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> Service.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> Service.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
