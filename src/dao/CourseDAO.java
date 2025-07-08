package dao;

import db.DBConnection;
import model.Course;
import java.sql.*;
import java.util.*;

public class CourseDAO {
    public void addCourse(Course c) {
        String sql = "INSERT INTO courses(name, duration) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getDuration());
            ps.executeUpdate();
            System.out.println("Course added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(int id, String name, String duration) {
        String sql = "UPDATE courses SET name = ?, duration = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, duration);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Course updated.");
            else System.out.println("Course not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(int id) {
        String deleteEnrollments = "DELETE FROM enrollments WHERE course_id = ?";
        String deleteCourse = "DELETE FROM courses WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            try (PreparedStatement ps1 = con.prepareStatement(deleteEnrollments)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
            }
            try (PreparedStatement ps2 = con.prepareStatement(deleteCourse)) {
                ps2.setInt(1, id);
                int rows = ps2.executeUpdate();
                if (rows > 0) System.out.println("Course deleted.");
                else System.out.println("Course not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getString("duration")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
