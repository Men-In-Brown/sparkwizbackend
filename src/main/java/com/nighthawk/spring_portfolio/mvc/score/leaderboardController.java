package com.nighthawk.spring_portfolio.mvc.score;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class leaderboardController {

    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/{gametype}")
    public ResponseEntity<?> getLeaderboardByGameType(@PathVariable String gametype) {
        String fileName = gametype.toLowerCase() + "Scores.json";
        File file = new File(fileName);

        // Validate gametype
        if (!isValidGameType(gametype)) {
            return ResponseEntity.badRequest().body("Invalid game type: " + gametype);
        }

        if (!file.exists()) {
            return ResponseEntity.ok(new ArrayList<>()); // Return an empty list if no scores are available
        }

        try {
            List<?> scores = mapper.readValue(file, new TypeReference<List<?>>() {});
            return ResponseEntity.ok(scores);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error retrieving scores: " + e.getMessage());
        }
    }

    private boolean isValidGameType(String gametype) {
        // List of valid game types
        List<String> validGameTypes = List.of("binary", "sass", "flask", "boolean");
        return validGameTypes.contains(gametype.toLowerCase());
    }
}
