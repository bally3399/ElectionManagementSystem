package africa.semicolon.com.electionManagementSystem.services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
