
package dao;

import model.Student;
import interfaces.StudentOperations;
import java.util.*;

public class StudentDAO implements StudentOperations {
    private List<Student> studentList = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        studentList.add(student);
    }

    @Override
    public boolean removeStudent(int rollNo) {
        return studentList.removeIf(s -> s.getRollNo() == rollNo);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }
}
    