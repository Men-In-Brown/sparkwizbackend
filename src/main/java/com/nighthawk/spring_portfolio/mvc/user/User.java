package com.nighthawk.spring_portfolio.mvc.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;


import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Convert(attributeName ="user", converter = JsonType.class)
public class User {

    // automatic unique identifier for User record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // username, password, roles are key attributes for login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    private String username;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    // To be implemented
    //@ManyToMany(fetch = EAGER)
    //private Collection<PersonRole> roles = new ArrayList<>();

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": "2200",
            "steps": "8000"
        }
    }
    */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Map<String, String>> stats = new HashMap<>();

    // Constructor used when building object from an API
    public User(String username, String password, String name, Date dob) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
    }

    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears();
        }
        return -1;
    }

    // Initialize static test data
    public static User[] init() {
        // basics of class construction
        User u1 = new User();
        u1.setName("Thomas Edison");
        u1.setUsername("toby@gmail.com");
        u1.setPassword("123Toby!");
        // adding Note to notes collection
        try {  // All data that converts formats could fail
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1840");
            u1.setDob(d);
        } catch (Exception e) {
            // no actions as dob default is good enough
        }

        User u2 = new User();
        u2.setName("Alexander Graham Bell");
        u2.setUsername("lexb@gmail.com");
        u2.setPassword("123LexB!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1845");
            u2.setDob(d);
        } catch (Exception e) {
        }

        User u3 = new User();
        u3.setName("Nikola Tesla");
        u3.setUsername("niko@gmail.com");
        u3.setPassword("123Niko!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1850");
            u3.setDob(d);
        } catch (Exception e) {
        }

        User u4 = new User();
        u4.setName("Madam Curie");
        u4.setUsername("madam@gmail.com");
        u4.setPassword("123Madam!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1860");
            u4.setDob(d);
        } catch (Exception e) {
        }

        User u5 = new User();
        u5.setName("John Mortensen");
        u5.setUsername("jm1021@gmail.com");
        u5.setPassword("123Qwerty!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("10-21-1959");
            u5.setDob(d);
        } catch (Exception e) {
        }

        // Array definition and data initialization
        User users[] = {u1, u2, u3, u4, u5};
        return users;
    }

    public static void main(String[] args) {
        // obtain User from initializer
        User users[] = init();

        // iterate using "enhanced for loop"
        for (User user : users) {
            System.out.println(user);  // print object
        }
    }
}
