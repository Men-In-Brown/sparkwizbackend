package com.nighthawk.spring_portfolio.mvc.many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/many")
public class ManyApiController {

    @Autowired
    private ManyJpaRepository manyRepository;

    // Other dependencies and methods can be added based on your requirements

    @GetMapping("/")
    public ResponseEntity<List<Many>> getManyRecords() {
        return new ResponseEntity<>(manyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Many> getManyRecord(@PathVariable long id) {
        return manyRepository.findById(id)
                .map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Other CRUD methods and endpoints can be added based on your requirements
}
