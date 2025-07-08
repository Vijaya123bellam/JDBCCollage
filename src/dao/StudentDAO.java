package dao;

import db.DBConnection;
import model.Student;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    public void addStudent(Student s) {
        String sql = "INSERT INTO students(name, email) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.executeUpdate();
            System.out.println("Student added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String name, String email) {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Student updated.");
            else System.out.println("Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String deleteEnrollments = "DELETE FROM enrollments WHERE student_id = ?";
        String deleteStudent = "DELETE FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            // First delete from enrollments
            try (PreparedStatement ps1 = con.prepareStatement(deleteEnrollments)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
            }

            // Then delete the student
            try (PreparedStatement ps2 = con.prepareStatement(deleteStudent)) {
                ps2.setInt(1, id);
                int rows = ps2.executeUpdate();
                if (rows > 0) System.out.println("Student deleted successfully.");
                else System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
