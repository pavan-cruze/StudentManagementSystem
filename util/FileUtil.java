package util;

import java.io.*;
import java.util.*;
import model.Student;

public class FileUtil {

    // Save students to file in readable format, sorted by Roll No
    public static void saveToFile(List<Student> students, String path) throws IOException {
        // Sort by roll number before saving
        students.sort(Comparator.comparingInt(Student::getRollNo));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Student student : students) {
                writer.write(student.getRollNo() + "," + student.getName() + "," + student.getMarks());
                writer.newLine();
            }
        }
    }

    // Read students from file and return as List
    public static List<Student> readFromFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) return null;

        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int rollNo = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        double marks = Double.parseDouble(parts[2].trim());
                        students.add(new Student(name, rollNo, marks));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid line: " + line);
                    }
                }
            }
        }
        return students;
    }
}
