package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
@CrossOrigin({"http://127.0.0.1:4100", "https://sortingminiproject.github.io"})
public class questionsController {

    // Array of boolean questions
    String[] booleanQuestions = {
        "What is the result of True and False in Python?",
        "What does the 'not' operator do to a boolean value?",
        "How can you check if two values are not equal in Python?",
        "What is the outcome of '5 > 3 or 2' in Python?",
        "How does Python evaluate 'not True or False'?",
        "What is the result of 'True and not False'?",
        "How do you check if a value is greater than or equal to another in Python?",
        "What does 'False or True and False' evaluate to?",
        "Can you compare strings using boolean operators in Python?",
        "How do you determine if a list is empty using a boolean expression?",
        "What is short-circuit evaluation in the context of boolean operations?",
        "How can you use the '==' operator in a boolean expression?",
        "What is the boolean value of an empty string in Python?",
        "How do you check if a number is within a range using boolean expressions?",
        "What is the precedence of boolean operators ('and', 'or', 'not') in Python?",
        "How can you use boolean operators with conditional statements in Python?",
        "What does the expression 'not (True and False)' evaluate to?",
        "How do you use the '!=' operator in a boolean expression?",
        "Can a boolean expression include arithmetic operations?",
        "What is the result of 'bool(None)' in Python?",
        "How can you check if a variable is 'None' using a boolean expression?",
        "What does '1 == 1 and 2 == 2' evaluate to?",
        "How does Python treat non-boolean values in boolean expressions?",
        "What is the outcome of 'bool(0)' and why?",
        "Can you use the 'is' operator in a boolean expression? How?",
        "What does 'False or any([False, False, True])' evaluate to?",
        "How can you use a boolean expression to check if a string contains a substring?",
        "What is the result of 'not 0' in Python?",
        "How can you use boolean expressions with Python lists?",
        "What does 'True and 5' evaluate to in Python?",
        "How do you compare two lists using boolean expressions?",
        "What is the boolean value of an empty list in Python?",
        "How can boolean expressions be used with loops in Python?",
        "Can you use boolean expressions to check dictionary membership in Python?",
        "What is the result of 'not True or not False'?",
        "How do you use boolean expressions to validate user input?",
        "What does '7 != 8 and 9 > 8' evaluate to?",
        "How can you use boolean expressions with function return values?",
        "What does 'bool(-1)' evaluate to and why?",
        "How do you check if all elements in a list are True using a boolean expression?",
        "What is the outcome of 'False or not (True and True)'?",
        "Can you use tuple comparisons in boolean expressions?",
        "How does the 'in' operator work with boolean expressions?",
        "What does 'not (False or True)' evaluate to?",
        "How can you use boolean expressions with list comprehensions?",
        "What is the result of 'True and (False or True)'?",
        "Can boolean expressions be nested? Provide an example.",
        "How do you use boolean expressions to check for multiple conditions?",
        "What does 'bool(False)' evaluate to in Python?"
    };
    
    String[] sassQuestions = {
        "What is SASS and what does it stand for?",
        "How do you declare a variable in SASS?",
        "What is the difference between SASS and SCSS?",
        "How can you create a mixin in SASS?",
        "What is the purpose of the @import directive in SASS?",
        "How do you use nested rules in SASS?",
        "What are partials in SASS and how do you use them?",
        "Explain the concept of inheritance in SASS with the @extend directive.",
        "How can you perform mathematical operations in SASS?",
        "What is the purpose of the @if directive in SASS?",
        "How do you implement loops in SASS?",
        "What are SASS functions and how do you define one?",
        "Explain the use of the @each directive in SASS.",
        "How can you manage colors in SASS using functions like lighten() and darken()?",
        "What is the role of the @use rule in SASS?",
        "How does SASS improve CSS maintainability?",
        "What is the difference between @use and @import in SASS?",
        "How can you create a grid layout using SASS mixins?",
        "What are the advantages of using SASS maps?",
        "How can you handle browser compatibility using SASS?",
        "What are the best practices for organizing SASS files in a large project?",
        "How do you comment in SASS and what types of comments are there?",
        "What is the significance of the !default flag in variable declarations?",
        "How can you use SASS in a React project?",
        "What tools are available for compiling SASS into CSS?",
        "Explain the concept of modules in SASS.",
        "How can SASS help in creating responsive designs?",
        "What is the difference between nested and root-level media queries in SASS?",
        "How do you handle errors and warnings in SASS?",
        "What are some popular frameworks or libraries that use SASS?"
    };

    
    
    

    // New endpoint for boolean questions
    @GetMapping("/boolean")
    public ResponseEntity<?> getBooleanQuestions() {
        var response = new Object() {
            public final String[] questions = booleanQuestions;
        };
        return ResponseEntity.ok(response);
    }

        // New endpoint for sass questions
        @GetMapping("/sass")
        public ResponseEntity<?> getSassQuestions() {
            var response = new Object() {
                public final String[] questions = sassQuestions;
            };
            return ResponseEntity.ok(response);
        }
}
