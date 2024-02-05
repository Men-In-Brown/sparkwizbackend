package com.nighthawk.spring_portfolio.mvc.many;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Many {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private MyClass myClass;

    private String attendanceStatus;

    // Constructors

    public Many(Person person, MyClass myClass, String attendanceStatus) {
        this.person = person;
        this.myClass = myClass;
        this.attendanceStatus = attendanceStatus;
    }

    // Getter and setter methods for all attributes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    // Other methods or business logic

    public String getPersonName() {
        return person != null ? person.getName() : null;
    }

    public String getClassName() {
        return myClass != null ? myClass.getClassName() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Many many = (Many) o;
        return Objects.equals(id, many.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Add more methods as needed based on your use case
}
