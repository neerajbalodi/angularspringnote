package com.noteitapi.api;

import com.noteitapi.mail.FeedbackSender;
import com.noteitapi.api.viewmodel.FeedbackViewModel;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {
    private FeedbackSender feedbackSender;

    public FeedbackController(FeedbackSender feedbackSender){
        this.feedbackSender=feedbackSender;
    }

    @PostMapping
    public void setFeedback(@Valid @RequestBody FeedbackViewModel feedbackViewModel,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback has errors; Can not send feedback;");
        }

        this.feedbackSender.sendFeedback(
                feedbackViewModel.getEmail(),
                feedbackViewModel.getName(),
                feedbackViewModel.getFeedback());
    }

}