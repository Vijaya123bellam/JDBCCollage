package service;

import dao.*;
import model.*;
import java.util.*;

public class CollegeService {
    private final Scanner sc = new Scanner(System.in);
    private final StudentDAO studentDAO = new StudentDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public void start() {
        while (true) {
            System.out.println("\n===== College Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. View Courses");
            System.out.println("5. Enroll Student");
            System.out.println("6. Update Student");
            System.out.println("7. Delete Student");
            System.out.println("8. Update Course");
            System.out.println("9. Delete Course");
            System.out.println("10. Exit");
            System.out.print("Choose option: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> addCourse();
                case 4 -> viewCourses();
                case 5 -> enrollStudent();
                case 6 -> updateStudent();
                case 7 -> deleteStudent();
                case 8 -> updateCourse();
                case 9 -> deleteCourse();
                case 10 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        studentDAO.addStudent(new Student(name, email));
    }

    private void viewStudents() {
        for (Student s : studentDAO.getAllStudents()) {
            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getEmail());
        }
    }

    private void addCourse() {
        System.out.print("Course name: ");
        String name = sc.nextLine();
        System.out.print("Duration: ");
        String duration = sc.nextLine();
        courseDAO.addCourse(new Course(name, duration));
    }

    private void viewCourses() {
        for (Course c : courseDAO.getAllCourses()) {
            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getDuration());
        }
    }

    private void enrollStudent() {
        System.out.print("Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Course ID: ");
        int courseId = sc.nextInt();
        enrollmentDAO.enroll(studentId, courseId);
    }

    private void updateStudent() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New name: ");
        String name = sc.nextLine();
        System.out.print("New email: ");
        String email = sc.nextLine();
        studentDAO.updateStudent(id, name, email);
    }

    private void deleteStudent() {
        System.out.print("Student ID to delete: ");
        int id = sc.nextInt();
        studentDAO.deleteStudent(id);
    }

    private void updateCourse() {
        System.out.print("Course ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New name: ");
        String name = sc.nextLine();
        System.out.print("New duration: ");
        String duration = sc.nextLine();
        courseDAO.updateCourse(id, name, duration);
    }

    private void deleteCourse() {
        System.out.print("Course ID to delete: ");
        int id = sc.nextInt();
        courseDAO.deleteCourse(id);
    }
}
