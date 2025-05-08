
package interfaces;

import model.Student;
import java.util.List;

public interface StudentOperations {
    void addStudent(Student student);
    boolean removeStudent(int rollNo);
    List<Student> getAllStudents();
}
    