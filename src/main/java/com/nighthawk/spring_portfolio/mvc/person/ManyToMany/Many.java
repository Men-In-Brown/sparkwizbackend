package com.nighthawk.spring_portfolio.mvc.many;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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

    // Other attributes related to the relationship can be added here

    // Constructors, getters, setters as needed
}
