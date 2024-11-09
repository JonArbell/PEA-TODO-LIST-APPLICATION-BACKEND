package com.myapp.pea.RestControllers.Authenticated.Feedback;

import com.myapp.pea.RequestResponseModels.FeedbackModel.FeedbackModel;
import com.myapp.pea.RestControllers.Authenticated.crudTodo.CrudTodo;
import com.myapp.pea.Services.Email.EmailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@Data
@AllArgsConstructor
public class Feedback {

    private final EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(CrudTodo.class);

    @PostMapping("/api/authenticated/send-feedback")
    public ResponseEntity<Map> sendContactEmail(@RequestBody FeedbackModel feedbackModel) {

        logger.info("Feedback : {}",feedbackModel);
        emailService.sendContactEmail(feedbackModel);

        var response = new HashMap<>();
        response.put("Response email","Successfull");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
