package com.nighthawk.spring_portfolio.mvc.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired 
    private UserJpaRepository repository;

    @Autowired
    private UserDetailsService userDetailsService;


    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            User user = optional.get();  // value from findByID
            return new ResponseEntity<>(user, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            User user = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(user, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    @PostMapping( "/post")
    public ResponseEntity<Object> postUser(@RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("name") String name) {
        // A person object WITHOUT ID will create a new record with default roles as student
        User user = new User(username, password, name);
        userDetailsService.save(user);
        return new ResponseEntity<>(username +" is created successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> userSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<User> list = repository.findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/setStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> userStats(@RequestBody final Map<String,Object> stat_map) {
        // find ID
        long id=Long.parseLong((String)stat_map.get("id"));  
        Optional<User> optional = repository.findById((id));
        if (optional.isPresent()) {  // Good ID
            User user = optional.get();  // value from findByID

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
            user.setStats(date_map);  // BUG, needs to be customized to replace if existing or append if new
            repository.save(user);  // conclude by writing the stats updates

            // return Person with update Stats
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        // return Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

}