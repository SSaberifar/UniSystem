import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private static final String CLASSES_FILE = "classes.txt";
    private static final String NOTIFICATIONS_FILE = "notifications.txt";
    private static final String TEACHERS_FILE = "teachers.txt";
    private static final String STUDENTS_FILE = "students.txt";

    // Method to save teachers to file
    public static void saveTeachers(List<Teacher> teachers) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(TEACHERS_FILE));
        for (Teacher teacher : teachers) {
            writer.write(teacher.getFirstName() + "," + teacher.getLastname() + "," + teacher.getUsername() + "," +
                    teacher.getEmail() + "," + teacher.getPhoneNumber() + "," + teacher.getRole() + "," +
                    teacher.getPass() + "," + teacher.getEducationalID());
            writer.newLine();
        }
        writer.close();
    }

    // Method to load teachers from file
    public static List<Teacher> loadTeachers() throws FileNotFoundException {
        List<Teacher> teachers = new ArrayList<>();
        Scanner scanner = new Scanner(new File(TEACHERS_FILE));
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            teachers.add(new Teacher(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
        }
        scanner.close();
        return teachers;
    }

    // Method to save students to file
    public static void saveStudents(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE));
        for (Student student : students) {
            writer.write(student.getFirstName() + "," + student.getLastname() + "," + student.getUsername() + "," +
                    student.getEmail() + "," + student.getPhoneNumber() + "," + student.getRole() + "," +
                    student.getPass() + "," + student.getEducationalID() + "," + student.getStudyField());
            writer.newLine();
        }
        writer.close();
    }

    // Method to load students from file
    public static List<Student> loadStudents() throws FileNotFoundException {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(new File(STUDENTS_FILE));
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            students.add(new Student(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
        }
        scanner.close();
        return students;
    }

    // Method to save classes to file
    public static void saveClasses(List<Unit> units) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(CLASSES_FILE));
        for (Unit unit : units) {
            writer.write(unit.toString());
            writer.newLine();
        }
        writer.close();
    }

    // Method to load classes from file
    public static List<Unit> loadClasses(Teacher teacher, List<Student> allStudents) throws FileNotFoundException {
        List<Unit> units = new ArrayList<>();
        Scanner scanner = new Scanner(new File(CLASSES_FILE));
        String unitName;
        Unit unit = null;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.contains(":")){
                String[] data = line.split(":");
                unitName = data[0];
                unit = new Unit(unitName, teacher);
                units.add(unit);
            }
            if (line.startsWith("Students=")) {
                String[] studentUsernames = line.substring(10).split(",");
                for (String username : studentUsernames) {
                    Student student = findStudentByUsername(allStudents, username);
                    if (student != null) {
                        unit.addStudent(student);
                    }
                }
            }
        }
        scanner.close();
        return units;
    }


    private static Student findStudentByUsername(List<Student> students, String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }


    // Method to save notifications to file
    public static void saveNotifications(List<Unit> units) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(NOTIFICATIONS_FILE));
        for (Unit unit : units) {
            writer.write(unit.getUnitName() + ": " + unit.getNotifications().toString());
            writer.newLine();
        }
        writer.close();
    }

    // Method to load notifications from file
    public static void loadNotifications(List<Unit> units) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(NOTIFICATIONS_FILE));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(": ");
            String unitName = data[0];
            String[] notifications = data[1].replace("[", "").replace("]", "").split(", ");
            for (Unit unit : units) {
                if (unit.getUnitName().equals(unitName)) {
                    for (String notification : notifications) {
                        unit.getNotifications().add(notification);
                    }
                }
            }
        }
        scanner.close();
    }
}
