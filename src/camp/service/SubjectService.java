
package camp.service;
import java.util.List;
import java.util.Scanner;

import camp.CampManagementApplication;
import camp.database.SubjectsData;
import camp.model.Subject;

public class SubjectService {
    private Scanner sc = new Scanner(System.in);
    List<Subject> subjects = CampManagementApplication.getSubjectsData().getSubjects();


    // 과목 이름 입력받아서 Subject 객체 반환하는 메서드
    public Subject getSubject() {
        System.out.println("시험과목을 입력하세요: ");
        String subjectName = sc.nextLine();

        for (int i = 0; i < subjects.size(); i++) {
            Subject tempSubject = subjects.get(i);
            if(tempSubject.getSubjectName().equals(subjectName)){
                System.out.println("해당 과목을 찾았습니다.");
                return tempSubject;
            }
        }
        System.out.println("해당 과목을 찾지못했습니다.");
        return null;
    }
    public Subject getSubject(String subjectName){
        for(Subject tempSubject : subjects){
            if(tempSubject.getSubjectName().equals(subjectName)){
                return tempSubject;
            }
        }
        return null;
    }
}
