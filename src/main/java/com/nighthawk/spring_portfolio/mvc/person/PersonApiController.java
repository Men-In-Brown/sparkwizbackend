package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.ManyToOne;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/person")
public class PersonApiController {

    @Autowired
    private PersonJpaRepository repository;

    @Autowired
    private PersonDetailsService personDetailsService;

    @GetMapping("/")
    public ResponseEntity<List<Person>> getPeople() {
        return new ResponseEntity<>(repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  
            Person person = optional.get();  
            return new ResponseEntity<>(person, HttpStatus.OK);  
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

    @ManyToOne
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  
            Person person = optional.get();  
            repository.deleteById(id);  
            return new ResponseEntity<>(person, HttpStatus.OK);  
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    @PostMapping( "/post")
    public ResponseEntity<Object> postPerson(@RequestParam("email") String email,
                                             @RequestParam("password") String password,
                                             @RequestParam("name") String name,
                                             @RequestParam("dob") String dobString,
                                             @RequestParam("stats") String stats
                                             ) {
        Date dob;
        try {
            dob = new SimpleDateFormat("MM-dd-yyyy").parse(dobString);
        } catch (Exception e) {
            return new ResponseEntity<>(dobString +" error; try MM-dd-yyyy", HttpStatus.BAD_REQUEST);
        }
        Person person = new Person(email, password, name, dob, stats);
        personDetailsService.save(person);
        return new ResponseEntity<>(email +" is created successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> personSearch(@RequestBody final Map<String,String> map) {
        String term = (String) map.get("term");
        List<Person> list = repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/setStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> personStats(@RequestBody final Map<String,Object> stat_map) {
        long id = Long.parseLong((String) stat_map.get("id"));  
        String date = (String) stat_map.get("date");
        
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  
            Person person = optional.get();  

            Map<String, Object> attributeMap = new HashMap<>();
            for (Map.Entry<String,Object> entry : stat_map.entrySet())  {
                if (!entry.getKey().equals("date") && !entry.getKey().equals("id")) {
                    attributeMap.put(entry.getKey(), entry.getValue());
                }
            }

            Map<String, Map<String, Object>> stats = person.getStats();
            
            if (stats.containsKey(date)) {
                stats.get(date).putAll(attributeMap); 
            } else {
                stats.put(date, attributeMap);
            }

            person.setStats(stats);
            repository.save(person);

            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
}
