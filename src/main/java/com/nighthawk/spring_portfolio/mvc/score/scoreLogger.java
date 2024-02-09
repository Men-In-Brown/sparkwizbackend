package com.nighthawk.spring_portfolio.mvc.score;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/gameScore")
public class scoreLogger {

    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @PostMapping("/submitScore")
    public ResponseEntity<?> submitScore(@RequestBody GameScore gameScore) {
        try {
            if (!isValidGameType(gameScore.getGametype())) {
                return ResponseEntity.badRequest().body("Invalid game type: " + gameScore.getGametype());
            }
            saveScore(gameScore);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error saving score: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(gameScore);
    }

    @GetMapping("/leaderboard/{gametype}")
    public ResponseEntity<?> getLeaderboard(@PathVariable String gametype) {
        if (!isValidGameType(gametype)) {
            return ResponseEntity.badRequest().body("Invalid game type: " + gametype);
        }

        String fileName = gametype.toLowerCase() + "Scores.json";
        File file = new File(fileName);

        if (!file.exists()) {
            return ResponseEntity.ok(new ArrayList<>()); // Return an empty list if no scores are available
        }

        try {
            List<GameScore> scores = mapper.readValue(file, new TypeReference<List<GameScore>>() {});
            scores.sort((s1, s2) -> s2.getScore() - s1.getScore()); // Sort scores in descending order
            return ResponseEntity.ok(scores);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error retrieving leaderboard: " + e.getMessage());
        }
    }

    private void saveScore(GameScore gameScore) throws IOException {
        String fileName = gameScore.getGametype().toLowerCase() + "Scores.json";
        File file = new File(fileName);

        List<GameScore> scores = new ArrayList<>();
        if (file.exists() && file.length() > 0) {
            scores = mapper.readValue(file, new TypeReference<List<GameScore>>() {});
        }

        GameScore existingScore = scores.stream()
                                        .filter(s -> s.getUsername().equals(gameScore.getUsername()))
                                        .findFirst()
                                        .orElse(null);

        if (existingScore != null) {
            if (gameScore.getScore() > existingScore.getScore()) {
                existingScore.setScore(gameScore.getScore());
            }
        } else {
            scores.add(gameScore);
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, scores);
    }

    private boolean isValidGameType(String gametype) {
        Set<String> validGameTypes = new HashSet<>(Arrays.asList("binary", "sass", "flask", "boolean"));
        return validGameTypes.contains(gametype.toLowerCase());
    }

    static class GameScore {
        private String username;
        private String gametype;
        private int score;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getGametype() {
            return gametype;
        }

        public void setGametype(String gametype) {
            this.gametype = gametype;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
