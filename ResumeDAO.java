import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResumeDAO {
    public void addResume(Resume resume) throws SQLException {
        String query = "INSERT INTO resumes (name, email, phone, summary, education, experience, skills) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, resume.getName());
            pstmt.setString(2, resume.getEmail());
            pstmt.setString(3, resume.getPhone());
            pstmt.setString(4, resume.getSummary());
            pstmt.setString(5, resume.getEducation());
            pstmt.setString(6, resume.getExperience());
            pstmt.setString(7, resume.getSkills());
            pstmt.executeUpdate();
        }
    }

    public Resume getResumeById(int id) throws SQLException {
        String query = "SELECT * FROM resumes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Resume resume = new Resume();
                    resume.setId(rs.getInt("id"));
                    resume.setName(rs.getString("name"));
                    resume.setEmail(rs.getString("email"));
                    resume.setPhone(rs.getString("phone"));
                    resume.setSummary(rs.getString("summary"));
                    resume.setEducation(rs.getString("education"));
                    resume.setExperience(rs.getString("experience"));
                    resume.setSkills(rs.getString("skills"));
                    return resume;
                }
            }
        }
        return null;
    }

    public List<Resume> getAllResumes() throws SQLException {
        List<Resume> resumes = new ArrayList<>();
        String query = "SELECT * FROM resumes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Resume resume = new Resume();
                resume.setId(rs.getInt("id"));
                resume.setName(rs.getString("name"));
                resume.setEmail(rs.getString("email"));
                resume.setPhone(rs.getString("phone"));
                resume.setSummary(rs.getString("summary"));
                resume.setEducation(rs.getString("education"));
                resume.setExperience(rs.getString("experience"));
                resume.setSkills(rs.getString("skills"));
                resumes.add(resume);
            }
        }
        return resumes;
    }

    public void updateResume(Resume resume) throws SQLException {
        String query = "UPDATE resumes SET name = ?, email = ?, phone = ?, summary = ?, education = ?, experience = ?, skills = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, resume.getName());
            pstmt.setString(2, resume.getEmail());
            pstmt.setString(3, resume.getPhone());
            pstmt.setString(4, resume.getSummary());
            pstmt.setString(5, resume.getEducation());
            pstmt.setString(6, resume.getExperience());
            pstmt.setString(7, resume.getSkills());
            pstmt.setInt(8, resume.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteResume(int id) throws SQLException {
        String query = "DELETE FROM resumes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
