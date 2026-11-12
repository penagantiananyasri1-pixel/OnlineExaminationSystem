
package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ExamController {

    private final List<Map<String, Object>> questions =
        new ArrayList<>();

    private final int[] correctAnswers = {1, 0, 2, 1, 0};

    public ExamController() {

        addQuestion(1, "Which language is used for Java development?",
            "Python", "Java", "C", "HTML");

        addQuestion(2, "Which tool is used for version control?",
            "Git", "Jenkins", "Docker", "Maven");

        addQuestion(3, "What is CI/CD?",
            "Coding", "Testing", "Continuous Integration and Delivery",
            "Database");

        addQuestion(4, "Which tool is used for containerization?",
            "Maven", "Docker", "Git", "Java");

        addQuestion(5, "Which tool is used for container orchestration?",
            "GitHub", "Kubernetes", "HTML", "CSS");
    }

    private void addQuestion(int id, String question,
            String a, String b, String c, String d) {

        Map<String, Object> q = new LinkedHashMap<>();

        q.put("id", id);
        q.put("question", question);
        q.put("options", List.of(a, b, c, d));

        questions.add(q);
    }

    @GetMapping("/questions")
    public List<Map<String, Object>> getQuestions() {
        return questions;
    }

    @PostMapping("/submit")
    public Map<String, Object> submitExam(
            @RequestBody Map<String, List<Integer>> request) {

        List<Integer> answers = request.get("answers");

        if (answers == null || answers.size() != questions.size()) {
            throw new IllegalArgumentException(
                "Please answer all questions."
            );
        }

        int score = 0;

        for (int i = 0; i < correctAnswers.length; i++) {
            if (Objects.equals(answers.get(i), correctAnswers[i])) {
                score++;
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("totalQuestions", questions.size());
        result.put("score", score);
        result.put("message", "Examination submitted successfully!");

        return result;
    }
}
