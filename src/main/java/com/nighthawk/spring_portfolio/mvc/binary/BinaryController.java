package com.nighthawk.spring_portfolio.mvc.binary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/game")
@CrossOrigin({"http://127.0.0.1:4100", "https://sortingminiproject.github.io"})
public class BinaryController {
    @GetMapping("/binary")
    public ResponseEntity<?> getBinary() {
        int[] binary = BinaryGenerate.getBinary();
        var response = new Object() {
            public final int[] numbers = binary;
        };
        return ResponseEntity.ok(response);
    }
}
