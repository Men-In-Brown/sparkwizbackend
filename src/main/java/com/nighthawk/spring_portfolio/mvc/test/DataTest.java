// package com.nighthawk.spring_portfolio.mvc.test;

// import java.text.SimpleDateFormat;
// import java.time.LocalDate;
// import java.time.Period;
// import java.time.ZoneId;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.Convert;
// import static jakarta.persistence.FetchType.EAGER;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.Size;

// import org.hibernate.annotations.JdbcTypeCode;
// import org.hibernate.type.SqlTypes;
// import org.springframework.format.annotation.DateTimeFormat;

// import com.vladmihalcea.hibernate.type.json.JsonType;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.NonNull;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Convert(attributeName ="test", converter = JsonType.class)
// public class DataTest {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     // state, password, roles are key attributes to login and authentication
//     @NotEmpty
//     @Size(min=5)
//     @Column(unique=true)
//     @State
//     private String state;

//     @NotEmpty
//     private String password;

//     // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Game (2 to 30 chars)") String game"
//     @NonNull
//     @Size(min = 2, max = 30, message = "Game (2 to 30 chars)")
//     private String game;

//     @DateTimeFormat(pattern = "yyyy-MM-dd")
//     private Date dob;

//     // To be implemented
//     @ManyToMany(fetch = EAGER)
//     private Collection<TestRole> roles = new ArrayList<>();

//     @JdbcTypeCode(SqlTypes.JSON)
//     @Column(columnDefinition = "jsonb")
//     private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

//     // Constructor used when building object from an API
//     public Test(String state, String password, String name, Date dob) {
//         this.state = state;
//         this.password = password;
//         this.game = game;
//         this.dob = dob;
//     }

//     // A custom getter to return age from dob attribute
//     public int getAge() {
//         if (this.dob != null) {
//             LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//             return Period.between(birthDay, LocalDate.now()).getYears(); }
//         return -1;
//     }

//     // Initialize static test data 
//     public static Test[] init() {

//         // basics of class construction
//         Test p1 = new Test();
//         p1.setGame("Thomas Edison");
//         p1.setState("Running");
//         p1.setPassword("123Toby!");
//         // adding Note to notes collection
//         try {  // All data that converts formats could fail
//             Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1840");
//             p1.setDob(d);
//         } catch (Exception e) {
//             // no actions as dob default is good enough
//         }

//         Test p2 = new Test();
//         p2.setGame("Alexander Graham Bell");
//         p2.setState("Running");
//         p2.setPassword("123LexB!");
//         try {
//             Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1845");
//             p2.setDob(d);
//         } catch (Exception e) {
//         }

//         Test p3 = new Test();
//         p3.setGame("Nikola Tesla");
//         p3.setState("niko@gmail.com");
//         p3.setPassword("123Niko!");
//         try {
//             Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1850");
//             p3.setDob(d);
//         } catch (Exception e) {
//         }

//         Test p4 = new Test();
//         p4.setGame("Madam Currie");
//         p4.setState("madam@gmail.com");
//         p4.setPassword("123Madam!");
//         try {
//             Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1860");
//             p4.setDob(d);
//         } catch (Exception e) {
//         }

//         Test p5 = new Test();
//         p5.setGame("Spark Admin");
//         p5.setRunning("spk@gmail.com");
//         p5.setPassword("spark");
//         try {
//             Date d = new SimpleDateFormat("MM-dd-yyyy").parse("09-11-2001");
//             p5.setDob(d);
//         } catch (Exception e) {
//         }

//         Test p6 = new Test();
//         p6.setGame("John Mortensen");
//         p6.setState("Running");
//         p6.setPassword("123Qwerty!");
//         try {
//             Date d = new SimpleDateFormat("MM-dd-yyyy").parse("09-14-2001");
//             p6.setDob(d);
//         } catch (Exception e) {
//         }

//         // Array definition and data initialization
//         Test tests[] = {p1, p2, p3, p4, p5, p6};
//         return(tests);
//     }

//     public static void main(String[] args) {
//         // obtain Test from initializer
//         Test tests[] = init();

//         // iterate using "enhanced for loop"
//         for( Test test : tests) {
//             System.out.println(test);  // print object
//         }
//     }

// }