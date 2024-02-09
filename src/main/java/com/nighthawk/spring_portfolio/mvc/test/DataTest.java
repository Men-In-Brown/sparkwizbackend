package com.nighthawk.spring_portfolio.mvc.test;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Convert;
import static jakarta.persistence.FetchType.EAGER;
import jakarta.validation.constraints.Email;
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
@Convert(attributeName ="test", converter = JsonType.class)
public class DataTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = EAGER)
    private Collection<DataRoles> roles = new ArrayList<>();

    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String state;

    @NotEmpty
    private String password;

    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    /** Custom constructor for DataTest when building a new DataTest object from an API call */
    public DataTest(String state, String password, String name, Date dob, DataRoles role) {
        this.state = state;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.roles.add(role);
    }
    
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    /** 1st telescoping method to create a DataTest object with USER role
     * @param name
     * @param state
     * @param password
     * @param dob
     * @return DataTest
     *  */ 
    public static DataTest createDataTest(String name, String state, String password, String dob) {
        return createDataTest(name, state, password, dob, Arrays.asList("USER"));
    }
    /** 2nd telescoping method to create a DateTest object with parameterized roles
     * @param roles 
     */
    public static DataTest createDataTest(String name, String state, String password, String dob, List<String> roleNames) {
        DataTest datatest = new DataTest();
        datatest.setName(name);
        datatest.setState(state);
        datatest.setPassword(password);
        try {
            Date date = new SimpleDateFormat("MM-dd-yyyy").parse(dob);
            datatest.setDob(date);
        } catch (Exception e) {
        }
    
        List<DataRoles> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            DataRoles role = new DataRoles(roleName);
            roles.add(role);
        }
        datatest.setRoles(roles);
    
        return datatest; 
    }
   
    /** Static method to initialize an array list of DataTest objects 
     * @return DataTest[], an array of DataTest objects
     */
    public static DataTest[] init() {
        ArrayList<DataTest> tests = new ArrayList<>();
        tests.add(createDataTest("Thomas Edison", "toby@gmail.com", "123toby", "01-01-1840", Arrays.asList("ADMIN", "USER", "TESTER")));
        tests.add(createDataTest("Alexander Graham Bell", "lexb@gmail.com", "123lex", "01-01-1847"));
        tests.add(createDataTest("Nikola Tesla", "niko@gmail.com", "123niko", "01-01-1850"));
        tests.add(createDataTest("Madam Currie", "madam@gmail.com", "123madam", "01-01-1860"));
        tests.add(createDataTest("Grace Hopper", "hop@gmail.com", "123hop", "12-09-1906"));
        tests.add(createDataTest("John Mortensen", "jm1021@gmail.com", "123Qwerty!", "10-21-1959", Arrays.asList("ADMIN")));
        return tests.toArray(new DataTest[0]);
    }

    /** Static method to print DataTest objects from an array
     * @param args, not used
     */
    public static void main(String[] args) {
        DataTest tests[] = init();

        for( DataTest datatest : tests) {
            System.out.println(datatest);  // print object
        }
    }

}