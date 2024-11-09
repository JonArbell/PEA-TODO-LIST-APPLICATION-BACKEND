package com.myapp.pea.Services.Email;

import com.myapp.pea.RequestResponseModels.FeedbackModel.FeedbackModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.username}")
    private String email;

    private final JavaMailSender mailSender;

    public void sendContactEmail(FeedbackModel feedbackModel) {

        var fromUserMessage = new SimpleMailMessage();

        var ui = feedbackModel.isUserInterfaceCheck() ? "Ui : Check" : "Ui : Not check";
        var functionality = feedbackModel.isFunctionalityCheck() ? "Functionality : Check" : "Functionality : Not " +
                "check";
        var performance = feedbackModel.isPerformanceCheck() ? "Performance : Check" : "Performance : Not check";
        var ux = feedbackModel.isUserExperienceCheck() ? "Ux : Check" : "Ux : Not check";
        var other = feedbackModel.getOther().isEmpty() || feedbackModel.getOther().isBlank() ? "Other : Empty" :
                "Other : "+feedbackModel.getOther();

        var feedbackTypes = ui+"\n"+functionality+"\n"+performance+"\n"+ux+"\n"+other;

        var finalMessage =
                "Email from : "+feedbackModel.getEmail()+"\n\n\nFeedback Type : \n\n"+feedbackTypes+"\n\n\nMessage :" +
                        " "+feedbackModel.getMessage();

        fromUserMessage.setTo(email);
        fromUserMessage.setSubject(feedbackModel.getSubject());
        fromUserMessage.setText(finalMessage);

        mailSender.send(fromUserMessage);

        var fromAdminMessage = new SimpleMailMessage();

        fromAdminMessage.setTo(feedbackModel.getEmail());
        fromAdminMessage.setText("Thank you for your feedback and for being part of our journey! We’re continuously " +
                        "looking for ways to enhance the experience on our website, and feedback like yours helps us " +
                "grow and improve. Please know that your input is appreciated, and we’re excited to keep evolving to " +
                        "meet your needs. Feel free to reach out anytime with more thoughts!\n\n\nOnce again, thank " +
                "you for your feedback and for helping us make PEA TO-DO LIST APPLICATION better. We're committed to " +
                "continuous improvement, and insights like yours drive that commitment forward. If you ever have more thoughts or suggestions, please feel free to reach out—we’d love to hear from you! In the meantime, feel free to explore more at https://pea-todo-list-application.onrender.com/.");
        fromAdminMessage.setSubject("PEA FEEDBACK");

        mailSender.send(fromAdminMessage);
    }

}
