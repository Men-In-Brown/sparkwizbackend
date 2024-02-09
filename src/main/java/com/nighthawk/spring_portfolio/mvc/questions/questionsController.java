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
    String[][] booleanQuestionsMCQ = {
        {
            "What is the result of True and False in Python?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: B) False"
        },
        {
            "What does the 'not' operator do to a boolean value?",
            "A) Changes True to False and vice versa",
            "B) Performs a logical 'AND'",
            "C) Performs a logical 'OR'",
            "D) None of the above",
            "Answer: A) Changes True to False and vice versa"
        },
        {
            "How can you check if two values are not equal in Python?",
            "A) =!",
            "B) <>",
            "C) !=",
            "D) ~=",
            "Answer: C) !="
        },
        {
            "What is the outcome of '5 > 3 or 2' in Python?",
            "A) True",
            "B) False",
            "C) 2",
            "D) SyntaxError",
            "Answer: A) True"
        },
        {
            "How does Python evaluate 'not True or False'?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: B) False"
        },
        {
            "What is the result of 'True and not False'?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: A) True"
        },
        {
            "How do you check if a value is greater than or equal to another in Python?",
            "A) >=",
            "B) =>",
            "C) =<",
            "D) <=",
            "Answer: A) >="
        },
        {
            "What does 'False or True and False' evaluate to?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: B) False"
        },
        {
            "Can you compare strings using boolean operators in Python?",
            "A) Yes, using == and !=",
            "B) Yes, but only with > and <",
            "C) No, strings cannot be compared",
            "D) Only if they are the same length",
            "Answer: A) Yes, using == and !="
        },
        {
            "How do you determine if a list is empty using a boolean expression?",
            "A) len(list) > 0",
            "B) list == None",
            "C) len(list) == 0",
            "D) list.isEmpty()",
            "Answer: C) len(list) == 0"
        },
        {
            "What is short-circuit evaluation in the context of boolean operations?",
            "A) Evaluating only the first operand",
            "B) Skipping type checking",
            "C) Evaluating the second operand only if the first isn't sufficient to determine the result",
            "D) Converting all operands to boolean",
            "Answer: C) Evaluating the second operand only if the first isn't sufficient to determine the result"
        },
        {
            "How can you use the '==' operator in a boolean expression?",
            "A) To assign values",
            "B) To compare values for equality",
            "C) As a shorthand for 'equals()'",
            "D) To ensure type and value equality",
            "Answer: B) To compare values for equality"
        },
        {
            "What is the boolean value of an empty string in Python?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: B) False"
        },
        // More questions can be added here following the same format...
    
        {
            "What does 'bool(False)' evaluate to in Python?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: B) False"
        }
    };
    
    // Note: For brevity, only 5 questions are shown here. You should expand this array to include all your questions.
    
    
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

    String[] flaskSetupQuestions = {
        "How do you install Flask in a Python environment?",
        "What is the command to create a new Flask application?",
        "How do you define a route in a Flask application?",
        "What is the purpose of the app.run() method in Flask?",
        "How can you enable debug mode in a Flask application?",
        "What is a view function in Flask and how do you create one?",
        "How do you retrieve query parameters in Flask from a URL?",
        "What is the Jinja2 template engine and how is it used in Flask?",
        "How do you render a template file in a Flask view function?",
        "What is the Flask-WTF extension and how do you use it to handle forms?",
        "How can you protect your Flask application against CSRF attacks?",
        "How do you connect a Flask application to a SQL database?",
        "What is Flask-SQLAlchemy and how does it simplify database operations?",
        "How do you create a database model in Flask?",
        "What is a Flask Blueprint and how do you use it to modularize your application?",
        "How can you manage user sessions in Flask?",
        "What are Flask extensions and can you name a few commonly used ones?",
        "How do you handle file uploads in a Flask application?",
        "What are some strategies for structuring a large Flask application?",
        "How do you deploy a Flask application to a production server like Heroku or AWS?"
    };
    
    
    

    // New endpoint for boolean questions
    @GetMapping("/boolean")
    public ResponseEntity<?> getBooleanQuestions() {
        var response = new Object() {
            public final String[] questions = booleanQuestionsMCQ;
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

    // New endpoint for sass questions
    @GetMapping("/flask")
    public ResponseEntity<?> getFkasjQuestions() {
        var response = new Object() {
            public final String[] questions = flaskSetupQuestions;
        };
        return ResponseEntity.ok(response);
    }
}
