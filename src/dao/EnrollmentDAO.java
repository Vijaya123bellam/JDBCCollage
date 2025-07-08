package dao;

import db.DBConnection;
import java.sql.*;

public class EnrollmentDAO {
    public void enroll(int studentId, int courseId) {
        String sql = "INSERT INTO enrollments(student_id, course_id) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            System.out.println("Enrollment successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
