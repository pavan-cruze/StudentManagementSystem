
package threads;

import dao.StudentDAO;
import util.FileUtil;

public class AutoSaveThread extends Thread {
    private StudentDAO dao;

    public AutoSaveThread(StudentDAO dao) {
        this.dao = dao;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(60000); // Save every 60 seconds
                FileUtil.saveToFile(dao.getAllStudents(), "data/students.txt");
                System.out.println("Auto-saved student data.");
            } catch (Exception e) {
                System.err.println("Auto-save failed: " + e.getMessage());
            }
        }
    }
}
    