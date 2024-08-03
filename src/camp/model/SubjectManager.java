package camp.model;
import java.util.List;
import java.util.Scanner;
import camp.CampManagementApplication;

public class SubjectManager extends Subject{
    private String subjectId;
    private String subjectName;
    private String subjectType;
    private static List<Subject> subjectStore = CampManagementApplication.getSubjectStore();
    private static Scanner sc = new Scanner(System.in);

    public SubjectManager(String seq, String subjectName, String subjectType) {
        super(seq, subjectName, subjectType);
        this.subjectId   = super.getSubjectId();
        this.subjectName = super.getSubjectName();
        this.subjectType = super.getSubjectType();
    }

    // 과목 이름 입력받아서 Subject 객체 반환하는 메서드
    public static Subject getSubject() {
        System.out.println("시험과목을 입력하세요: ");
        String subjectName = sc.nextLine();

        for (int i = 0; i < subjectStore.size(); i++) {
            Subject tempSubject = subjectStore.get(i);
            if(tempSubject.getSubjectName().equals(subjectName)){
                System.out.println("해당 Subject 객체를 찾았습니다.");
                return tempSubject;
            }
        }
        System.out.println("해당 Subject 객체를 찾지못했습니다.");
        return null;
    }
}
