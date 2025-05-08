import dao.StudentDAO;
import model.Student;
import threads.AutoSaveThread;
import util.FileUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        // Load data if exists
        try {
            List<Student> saved = FileUtil.readFromFile("data/students.txt");
            if (saved != null) {
                saved.forEach(dao::addStudent);
                System.out.println("Loaded saved students.");
            }
        } catch (Exception e) {
            System.out.println("No saved data found.");
        }

        // Start auto-save thread
        new AutoSaveThread(dao).start();

        while (true) {
            System.out.println("1. Add 2. Remove 3. View 4. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Roll No: ");
                    int roll = sc.nextInt();
                    System.out.print("Marks: ");
                    double marks = sc.nextDouble();
                    dao.addStudent(new Student(name, roll, marks));
                }
                case 2 -> {
                    System.out.print("Enter Roll No to remove: ");
                    int roll = sc.nextInt();
                    boolean removed = dao.removeStudent(roll);
                    System.out.println(removed ? "Removed." : "Not found.");
                }
                case 3 -> dao.getAllStudents().forEach(System.out::println);
                case 4 -> {
                    System.out.println("Exiting...");
                    // Save the current data before exit
                    try {
                        FileUtil.saveToFile(dao.getAllStudents(), "data/students.txt");
                        System.out.println("Data saved successfully.");
                    } catch (Exception e) {
                        System.err.println("Error saving file: " + e.getMessage());
                    }
                    sc.close();
                    return;
                }
            }
        }
    }
}
