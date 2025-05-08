
package util;

import java.io.*;
import java.util.List;
import model.Student;

public class FileUtil {
    public static void saveToFile(List<Student> students, String path) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(students);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Student> readFromFile(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        if (!file.exists()) return null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return (List<Student>) in.readObject();
        }
    }
}
    