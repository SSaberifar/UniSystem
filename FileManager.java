import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private static final String CLASSES_FILE = "Files/classes.txt";
    private static final String NOTIFICATIONS_FILE = "Files/notifications.txt";
    private static final String TEACHERS_FILE = "Files/teachers.txt";
    private static final String STUDENTS_FILE = "Files/students.txt";
    private static final String TASKS_FILE = "Files/tasks.txt";

    public static void saveTeachers(List<Teacher> teachers) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEACHERS_FILE))) {
            for (Teacher teacher : teachers) {
                writer.write(String.join(",",
                        teacher.getFirstName(), teacher.getLastname(), teacher.getUsername(),
                        teacher.getEmail(), teacher.getPhoneNumber(), teacher.getRole(),
                        teacher.getPass(), teacher.getEducationalID()));
                writer.newLine();
            }
        }
    }

    public static List<Teacher> loadTeachers() throws IOException {
        try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(TEACHERS_FILE)))) {
            List<Teacher> teachers = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                teachers.add(new Teacher(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
            }
            return teachers;
        }
    }

    public static void saveStudents(List<Student> students) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(STUDENTS_FILE))) {
            for (Student student : students) {
                writer.write(String.join(",",
                        student.getFirstName(), student.getLastname(), student.getUsername(),
                        student.getEmail(), student.getPhoneNumber(), student.getRole(),
                        student.getPass(), student.getEducationalID(), student.getStudyField()));
                writer.newLine();
            }
        }
    }

    public static List<Student> loadStudents() throws IOException {
        try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(STUDENTS_FILE)))) {
            List<Student> students = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                students.add(new Student(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
            }
            return students;
        }
    }

    public static void saveClasses(List<Unit> units) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CLASSES_FILE))) {
            for (Unit unit : units) {
                writer.write(unit.toString());
                writer.newLine();
            }
        }
    }

    public static List<Unit> loadClasses(Teacher teacher, List<Student> allStudents) throws IOException {
        List<Unit> units = new ArrayList<>();
        try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(CLASSES_FILE)))) {
            Unit unit = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(":")) {
                    String unitName = line.split(":")[0];
                    unit = new Unit(unitName, teacher);
                    units.add(unit);
                }
                if (line.startsWith("Students=")) {
                    String[] studentUsernames = line.substring(10).split(",");
                    for (String username : studentUsernames) {
                        Student student = findStudentByUsername(allStudents, username);
                        if (student != null && unit != null) {
                            unit.addStudent(student);
                        }
                    }
                }
            }
        }
        return units;
    }

    private static Student findStudentByUsername(List<Student> students, String username) {
        return students.stream()
                .filter(student -> student.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public static void saveQuestions(List<Unit> units) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(TASKS_FILE))) {
            for (Unit unit : units) {
                for (Task task : unit.getTasks()) {
                    if (task instanceof Question question) {
                        writer.write(unit.getUnitName() + ": " + question);
                        writer.newLine();
                    }
                }
            }
        }
    }

    public static void loadQuestions(List<Unit> units) throws IOException {
        try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(TASKS_FILE)))) {
            String unitName = "", questionDead = "", questionName = "", questionText = "", questionAns;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(": ")) {
                    unitName = line.split(": ")[0];
                }
                if (line.contains("=")) {
                    String[] keyValue = line.split("=");
                    switch (keyValue[0]) {
                        case "question deadline":
                            questionDead = keyValue[1];
                            break;
                        case "question name":
                            questionName = keyValue[1];
                            break;
                        case "question text":
                            questionText = keyValue[1];
                            break;
                        case "question answer":
                            questionAns = keyValue[1];
                            for (Unit unit : units) {
                                if (unit.getUnitName().equals(unitName)) {
                                    unit.getTasks().add(new Question(questionDead, questionText, questionAns, questionName, unit, unit.getTeacher()));
                                }
                            }
                            break;
                    }
                }
            }
        }
    }

    public static void saveNotifications(List<Unit> units) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(NOTIFICATIONS_FILE))) {
            for (Unit unit : units) {
                writer.write(unit.getUnitName() + ": " + unit.getNotifications().toString());
                writer.newLine();
            }
        }
    }

    public static void loadNotifications(List<Unit> units) throws IOException {
        try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(NOTIFICATIONS_FILE)))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(": ");
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
        }
    }
}
