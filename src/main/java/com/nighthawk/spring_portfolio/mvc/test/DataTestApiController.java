package com.nighthawk.spring_portfolio.mvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/test")
public class DataTestApiController {

    @Autowired
    private DataTestJpaRepository repository;

    @Autowired
    private DataTestDetailsService testDetailsService;

    /*
    GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<Test>> getPeople() {
        return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    /*
    GET individual Test using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Test> getTest(@PathVariable long id) {
        Optional<Test> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Test test = optional.get();  // value from findByID
            return new ResponseEntity<>(test, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

    /*
    DELETE individual Test using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Test> deleteTest(@PathVariable long id) {
        Optional<Test> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Test test = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(test, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    @PostMapping( "/post")
    public ResponseEntity<Object> postTest(@RequestParam("state") String state,
                                             @RequestParam("password") String password,
                                             @RequestParam("game") String game,
                                             @RequestParam("dob") String dobString) {
        Date dob;
        try {
            dob = new SimpleDateFormat("MM-dd-yyyy").parse(dobString);
        } catch (Exception e) {
            return new ResponseEntity<>(dobString +" error; try MM-dd-yyyy", HttpStatus.BAD_REQUEST);
        }
        // A test object WITHOUT ID will create a new record with default roles as student
        Test test = new Test(state, password, game, dob);
        testDetailsService.save(test);
        return new ResponseEntity<>(state +" is created successfully", HttpStatus.CREATED);
    }

    /*
    The testSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> testSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Test> list = repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*
    The testStats API adds stats by Date to Test table 
    */
    @PostMapping(value = "/setStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Test> testStats(@RequestBody final Map<String,Object> stat_map) {
        // find ID
        long id=Long.parseLong((String)stat_map.get("id"));  
        Optional<Test> optional = repository.findById((id));
        if (optional.isPresent()) {  // Good ID
            Test test = optional.get();  // value from findByID

            // Extract Attributes from JSON
            Map<String, Object> attributeMap = new HashMap<>();
            for (Map.Entry<String,Object> entry : stat_map.entrySet())  {
                // Add all attribute other thaN "date" to the "attribute_map"
                if (!entry.getKey().equals("date") && !entry.getKey().equals("id"))
                    attributeMap.put(entry.getKey(), entry.getValue());
            }

            // Set Date and Attributes to SQL HashMap
            Map<String, Map<String, Object>> date_map = new HashMap<>();
            date_map.put( (String) stat_map.get("date"), attributeMap );
            test.setStats(date_map);  // BUG, needs to be customized to replace if existing or append if new
            repository.save(test);  // conclude by writing the stats updates

            // return Test with update Stats
            return new ResponseEntity<>(test, HttpStatus.OK);
        }
        // return Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
}
