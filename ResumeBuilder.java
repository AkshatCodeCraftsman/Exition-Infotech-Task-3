import java.sql.SQLException;
import java.util.List;

public class ResumeBuilder
{
    public static void main(String[] args)
    {
        ResumeDAO resumeDAO = new ResumeDAO();

        // Add a new resume
        Resume newResume = new Resume();
        newResume.getName("John Doe");
        newResume.setEmail("john.doe@example.com");
        newResume.setPhone("123-456-7890");
        newResume.setSummary("Experienced software developer...");
        newResume.setEducation("B.Sc. in Computer Science");
        newResume.setExperience("5 years at XYZ Company");
        newResume.setSkills("Java, SQL, HTML, CSS, JavaScript");

        try {

            // Retrieve and print all resumes
            List<Resume> resumes = resumeDAO.getAllResumes();
            for (Resume resume : resumes) {
                System.out.println(resume.getName() + " - " + resume.getEmail());
            }

            // Update a resume
            Resume existingResume = resumeDAO.getResumeById(1);
            if (existingResume != null) {
                existingResume.setPhone("987-654-3210");
                resumeDAO.updateResume(existingResume);
            }

            // Delete a resume
            resumeDAO.deleteResume(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
