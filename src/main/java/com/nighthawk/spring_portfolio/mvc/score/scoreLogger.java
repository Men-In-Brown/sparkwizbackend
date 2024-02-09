package com.nighthawk.spring_portfolio.mvc.score;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booleanGame")
@CrossOrigin(origins = {"http://127.0.0.1:4100", "https://sortingminiproject.github.io"})
public class scoreLogger {

    // Static list to store boolean questions
    private static final List<BooleanQuestion> questions = new ArrayList<>();

    static {
        // Initialize your 50 boolean questions here
        for (int i = 1; i <= 50; i++) {
            questions.add(new BooleanQuestion("Question " + i + " statement here", "A")); // Replace "A" with the correct answer for each question
        }
    }

    // Endpoint to get all boolean questions
    @GetMapping("/questions")
    public ResponseEntity<?> getQuestions() {
        return ResponseEntity.ok(questions);
    }

    // Endpoint to submit an answer to a specific question
    @PostMapping("/submitAnswer")
    public ResponseEntity<?> submitAnswer(@RequestBody AnswerSubmission submission) {
        BooleanQuestion question = questions.get(submission.getQuestion() - 1); // Adjust for zero-based index
        boolean isCorrect = question.getAnswer().equalsIgnoreCase(submission.getAnswer());
        var response = new Object() {
            public final boolean correct = isCorrect;
        };
        return ResponseEntity.ok(response);
    }

    // Inner class for boolean questions
    static class BooleanQuestion {
        private String statement;
        private String answer;

        public BooleanQuestion(String statement, String answer) {
            this.statement = statement;
            this.answer = answer;
        }

        public String getStatement() {
            return statement;
        }

        public String getAnswer() {
            return answer;
        }
    }

    // Inner class for answer submission
    static class AnswerSubmission {
        private int question;
        private String answer;

        public int getQuestion() {
            return question;
        }

        public void setQuestion(int question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
