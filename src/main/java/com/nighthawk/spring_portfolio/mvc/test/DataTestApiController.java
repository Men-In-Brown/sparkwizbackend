// package com.nighthawk.spring_portfolio.mvc.test;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.text.SimpleDateFormat;

// @RestController
// @RequestMapping("/api/test")
// public class DataTestApiController {

//     @Autowired
//     private DataTestJpaRepository repository;

//     // @Autowired
//     // private PersonDetailsService personDetailsService;

//     /*
//     GET List of People
//      */
//     @GetMapping("/")
//     public ResponseEntity<List<DataTest>> getPeople() {
//         return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
//     }

//     /*
//     GET individual DataTest using ID
//      */
//     @GetMapping("/{id}")
//     public ResponseEntity<DataTest> getDataTest(@PathVariable long id) {
//         Optional<DataTest> optional = repository.findById(id);
//         if (optional.isPresent()) {  // Good ID
//             DataTest dataTest = optional.get();  // value from findByID
//             return new ResponseEntity<>(dataTest, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
//         }
//         // Bad ID
//         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
//     }

//     /*
//     DELETE individual DataTest using ID
//      */
//     @DeleteMapping("/delete/{id}")
//     public ResponseEntity<DataTest> deleteDataTest(@PathVariable long id) {
//         Optional<DataTest> optional = repository.findById(id);
//         if (optional.isPresent()) {  // Good ID
//             DataTest dataTest = optional.get();  // value from findByID
//             repository.deleteById(id);  // value from findByID
//             return new ResponseEntity<>(dataTest, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
//         }
//         // Bad ID
//         return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//     }

//     /*
//     POST Aa record by Requesting Parameters from URI
//      */
//     @PostMapping( "/post")
//     public ResponseEntity<Object> postDataTest(@RequestParam("state") String state,
//                                              @RequestParam("password") String password,
//                                              @RequestParam("name") String name,
//                                              @RequestParam("dob") String dobString) {
//         Date dob;
//         try {
//             dob = new SimpleDateFormat("MM-dd-yyyy").parse(dobString);
//         } catch (Exception e) {
//             return new ResponseEntity<>(dobString +" error; try MM-dd-yyyy", HttpStatus.BAD_REQUEST);
//         }
//         // A dataTest object WITHOUT ID will create a new record with default roles as student
//         DataTest dataTest = new DataTest(state, password, name, dob); /*personDetailsService.findRole("USER")*/
//         // dataTestDetailsService.save(dataTest);
//         return new ResponseEntity<>(state +" is created successfully", HttpStatus.CREATED);
//     }

//     @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<Object> dataTestSearch(@RequestBody final Map<String,String> map) {
//         String term = (String) map.get("term");

//         List<DataTest> list = repository.findByNameContainingIgnoreCaseOrStateContainingIgnoreCase(term, term);

//         return new ResponseEntity<>(list, HttpStatus.OK);
//     }

//     /*
//     The dataTest stats API adds stats by Date to DataTest table 
//     */
//     @PostMapping(value = "/setStats", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<DataTest> personStats(@RequestBody final Map<String,Object> stat_map) {
//         // find ID
//         long id=Long.parseLong((String)stat_map.get("id"));  
//         Optional<DataTest> optional = repository.findById((id));
//         if (optional.isPresent()) {  // Good ID
//             DataTest dataTest = optional.get();  // value from findByID

//             // Extract Attributes from JSON
//             Map<String, Object> attributeMap = new HashMap<>();
//             for (Map.Entry<String,Object> entry : stat_map.entrySet())  {
//                 // Add all attribute other thaN "date" to the "attribute_map"
//                 if (!entry.getKey().equals("date") && !entry.getKey().equals("id"))
//                     attributeMap.put(entry.getKey(), entry.getValue());
//             }

//             // Set Date and Attributes to SQL HashMap
//             Map<String, Map<String, Object>> date_map = new HashMap<>();
//             date_map.put( (String) stat_map.get("date"), attributeMap );
//             dataTest.setStats(date_map);  // BUG, needs to be customized to replace if existing or append if new
//             // repository.save(data);  // conclude by writing the stats updates

//             // return DataTest with update Stats
//             return new ResponseEntity<>(dataTest, HttpStatus.OK);
//         }
//         // return Bad ID
//         return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//     }
// }