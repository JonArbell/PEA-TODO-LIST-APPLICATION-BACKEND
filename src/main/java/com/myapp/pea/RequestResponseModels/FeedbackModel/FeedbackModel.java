package com.myapp.pea.RequestResponseModels.FeedbackModel;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FeedbackModel {

    // Basics
    private String email;
    private String fullName;
    private String message;
    private String subject;

    // Feedback type
    private boolean userInterfaceCheck;
    private boolean functionalityCheck;
    private boolean performanceCheck;
    private boolean userExperienceCheck;
    private String other;
}

