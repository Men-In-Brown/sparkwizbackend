package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

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
        {
            "What does 'bool(False)' evaluate to in Python?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: B) False"
        }
    };
    
    String[][] sassQuestionsMCQ = {
        {
            "What is SASS and what does it stand for?",
            "A) Style And Syntax Sheet",
            "B) Syntactically Awesome Style Sheets",
            "C) Simple And Secure Styles",
            "D) Systematic Advanced Style Syntax",
            "Answer: B) Syntactically Awesome Style Sheets"
        },
        {
            "How do you declare a variable in SASS?",
            "A) var variableName: value;",
            "B) $variableName: value;",
            "C) @variableName: value;",
            "D) variableName = value;",
            "Answer: B) $variableName: value;"
        },
        {
            "What is the difference between SASS and SCSS?",
            "A) SCSS allows CSS syntax, SASS does not",
            "B) SASS is a newer version",
            "C) SCSS is more powerful",
            "D) No difference, just naming conventions",
            "Answer: A) SCSS allows CSS syntax, SASS does not"
        },
        {
            "How can you create a mixin in SASS?",
            "A) @mixin mixinName { ... }",
            "B) @function mixinName { ... }",
            "C) @include mixinName { ... }",
            "D) mixin mixinName { ... }",
            "Answer: A) @mixin mixinName { ... }"
        },
        {
            "What is the purpose of the @import directive in SASS?",
            "A) To import mixins",
            "B) To include CSS files",
            "C) To load SASS/SCSS files",
            "D) To import JavaScript files",
            "Answer: C) To load SASS/SCSS files"
        },
        {
            "How do you use nested rules in SASS?",
            "A) By using the @nest directive",
            "B) By placing selectors inside other selectors",
            "C) By separating selectors with a comma",
            "D) By using the & symbol",
            "Answer: B) By placing selectors inside other selectors"
        },
        {
            "What are partials in SASS and how do you use them?",
            "A) Variables that are partially used",
            "B) Incomplete CSS rules",
            "C) Small SASS files that can be included in other SASS files",
            "D) A method to partially import a file",
            "Answer: C) Small SASS files that can be included in other SASS files"
        },
        {
            "Explain the concept of inheritance in SASS with the @extend directive.",
            "A) Allowing one selector to inherit the styles of another",
            "B) Extending SASS with JavaScript",
            "C) Increasing the specificity of a selector",
            "D) Copying styles from one file to another",
            "Answer: A) Allowing one selector to inherit the styles of another"
        },
        {
            "How can you perform mathematical operations in SASS?",
            "A) Using JavaScript",
            "B) With special SASS math functions",
            "C) Directly in the CSS",
            "D) By using CSS variables",
            "Answer: B) With special SASS math functions"
        },
        {
            "What is the purpose of the @if directive in SASS?",
            "A) To check the type of a variable",
            "B) To import files conditionally",
            "C) To apply styles based on conditions",
            "D) To define SASS functions",
            "Answer: C) To apply styles based on conditions"
        }
    };
    
    
    
    String[][] flaskSetupQuestionsMCQ = {
        {
            "How do you install Flask in a Python environment?",
            "A) npm install flask",
            "B) pip install flask",
            "C) docker pull flask",
            "D) brew install flask",
            "Answer: B) pip install flask"
        },
        {
            "What is the command to create a new Flask application?",
            "A) flask startapp",
            "B) flask new app",
            "C) flask create app",
            "D) flask run",
            "Answer: D) flask run"
        },
        {
            "How do you define a route in a Flask application?",
            "A) @app.route('/route')",
            "B) @flask.route('/route')",
            "C) @route('/route')",
            "D) @app.get('/route')",
            "Answer: A) @app.route('/route')"
        },
        {
            "What is the purpose of the app.run() method in Flask?",
            "A) To define route functions",
            "B) To start the Flask application",
            "C) To install Flask dependencies",
            "D) To configure the application",
            "Answer: B) To start the Flask application"
        },
        {
            "How can you enable debug mode in a Flask application?",
            "A) app.debug = True",
            "B) app.config['DEBUG'] = True",
            "C) FLASK_DEBUG = 1",
            "D) Both A and B",
            "Answer: D) Both A and B"
        },
        {
            "What is a view function in Flask and how do you create one?",
            "A) A function that returns Flask views, created with @view('/path')",
            "B) A function that returns HTML templates, created with @template('/path')",
            "C) A function that handles a route, created with @app.route('/path')",
            "D) A function that processes form data, created with @app.form('/path')",
            "Answer: C) A function that handles a route, created with @app.route('/path')"
        },
        {
            "How do you retrieve query parameters in Flask from a URL?",
            "A) request.query['parameter']",
            "B) request.args.get('parameter')",
            "C) request.get('parameter')",
            "D) request.params['parameter']",
            "Answer: B) request.args.get('parameter')"
        },
        {
            "What is the Jinja2 template engine and how is it used in Flask?",
            "A) A CSS framework for Flask",
            "B) A JavaScript engine for Flask applications",
            "C) A template engine for rendering HTML templates",
            "D) A database engine for Flask",
            "Answer: C) A template engine for rendering HTML templates"
        },
        {
            "How do you render a template file in a Flask view function?",
            "A) render_template('template.html')",
            "B) flask.render('template.html')",
            "C) app.render('template.html')",
            "D) template.render('template.html')",
            "Answer: A) render_template('template.html')"
        },
        {
            "What is the Flask-WTF extension and how do you use it to handle forms?",
            "A) A Flask extension for creating and managing forms",
            "B) A Flask tool for debugging",
            "C) A Flask extension for Windows Task Force",
            "D) A Flask widget toolkit",
            "Answer: A) A Flask extension for creating and managing forms"
        }
    };

    String[][] binaryQuestionsMCQ = {
        {
            "What is the binary representation of 10 in decimal?",
            "A) 1010",
            "B) 1100",
            "C) 1000",
            "D) 1110",
            "Answer: A) 1010"
        },
        {
            "What is the decimal equivalent of the binary number 1101?",
            "A) 11",
            "B) 12",
            "C) 13",
            "D) 14",
            "Answer: C) 13"
        },
        {
            "How many bits are in a byte?",
            "A) 4",
            "B) 8",
            "C) 16",
            "D) 32",
            "Answer: B) 8"
        },
        {
            "What is the binary representation of the decimal number 25?",
            "A) 11001",
            "B) 10011",
            "C) 10101",
            "D) 11101",
            "Answer: A) 11001"
        },
        // {
        //     "What is the result of bitwise AND operation between 1010 and 1100?",
        //     "A) 1000",
        //     "B) 1110",
        //     "C) 1100",
        //     "D) 1010",
        //     "Answer: A) 1000"
        // },
        {
            "How do you represent the number 7 in binary?",
            "A) 1110",
            "B) 101",
            "C) 111",
            "D) 110",
            "Answer: C) 111"
        },
        {
            "What is the binary representation of the hexadecimal number 2F?",
            "A) 101111",
            "B) 101110",
            "C) 111100",
            "D) 101001",
            "Answer: A) 101111"
        },
        // {
        //     "What is the result of bitwise XOR operation between 1011 and 1101?",
        //     "A) 0110",
        //     "B) 1111",
        //     "C) 1001",
        //     "D) 0010",
        //     "Answer: C) 1001"
        // },
        {
            "How many unique values can be represented with 8 bits?",
            "A) 16",
            "B) 64",
            "C) 128",
            "D) 256",
            "Answer: C) 256"
        },
        // {
        //     "What is the binary representation of the octal number 76?",
        //     "A) 101110",
        //     "B) 111110",
        //     "C) 111100",
        //     "D) 101100",
        //     "Answer: D) 101100"
        // }
    };

    String[][] allQuestionsMCQs = {
        {
            "What is the result of True and False in Java?",
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
            "How can you check if two values are not equal in Java?",
            "A) =!",
            "B) <>",
            "C) !=",
            "D) ~=",
            "Answer: C) !="
        },
        {
            "What is the outcome of '5 > 3 or 2' in Java?",
            "A) True",
            "B) False",
            "C) 2",
            "D) SyntaxError",
            "Answer: A) True"
        },
        {
            "How does Java evaluate 'not True or False'?",
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
            "How do you check if a value is greater than or equal to another in Java?",
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
            "Can you compare strings using boolean operators in Java?",
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
            "What is the boolean value of an empty string in Java?",
            "A) True",
            "B) False",
            "C) None",
            "D) Error",
            "Answer: B) False"
        },
        {
            "What is the binary representation of 10 in decimal?",
            "A) 1010",
            "B) 1100",
            "C) 1000",
            "D) 1110",
            "Answer: A) 1010"
        },
        {
            "What is the decimal equivalent of the binary number 1101?",
            "A) 11",
            "B) 12",
            "C) 13",
            "D) 14",
            "Answer: C) 13"
        },
        {
            "How many bits are in a byte?",
            "A) 4",
            "B) 8",
            "C) 16",
            "D) 32",
            "Answer: B) 8"
        },
        {
            "What is the binary representation of the decimal number 25?",
            "A) 11001",
            "B) 10011",
            "C) 10101",
            "D) 11101",
            "Answer: A) 11001"
        },
        // {
        //     "What is the result of bitwise AND operation between 1010 and 1100?",
        //     "A) 1000",
        //     "B) 1110",
        //     "C) 1100",
        //     "D) 1010",
        //     "Answer: A) 1000"
        // },
        {
            "How do you represent the number 7 in binary?",
            "A) 1110",
            "B) 101",
            "C) 111",
            "D) 110",
            "Answer: C) 111"
        },
        {
            "What is the binary representation of the hexadecimal number 2F?",
            "A) 101111",
            "B) 101110",
            "C) 111100",
            "D) 101001",
            "Answer: A) 101111"
        },
        // {
        //     "What is the result of bitwise XOR operation between 1011 and 1101?",
        //     "A) 0110",
        //     "B) 1111",
        //     "C) 1001",
        //     "D) 0010",
        //     "Answer: C) 1001"
        // },
        {
            "How many unique values can be represented with 8 bits?",
            "A) 16",
            "B) 64",
            "C) 128",
            "D) 256",
            "Answer: C) 256"
        },
        // {
        //     "What is the binary representation of the octal number 76?",
        //     "A) 101110",
        //     "B) 111110",
        //     "C) 111100",
        //     "D) 101100",
        //     "Answer: D) 101100"
        // }
        // {
        //     "How do you install Flask in a Python environment?",
        //     "A) npm install flask",
        //     "B) pip install flask",
        //     "C) docker pull flask",
        //     "D) brew install flask",
        //     "Answer: B) pip install flask"
        // },
        // {
        //     "What is the command to create a new Flask application?",
        //     "A) flask startapp",
        //     "B) flask new app",
        //     "C) flask create app",
        //     "D) flask run",
        //     "Answer: D) flask run"
        // },
        // {
        //     "How do you define a route in a Flask application?",
        //     "A) @app.route('/route')",
        //     "B) @flask.route('/route')",
        //     "C) @route('/route')",
        //     "D) @app.get('/route')",
        //     "Answer: A) @app.route('/route')"
        // },
        // {
        //     "What is the purpose of the app.run() method in Flask?",
        //     "A) To define route functions",
        //     "B) To start the Flask application",
        //     "C) To install Flask dependencies",
        //     "D) To configure the application",
        //     "Answer: B) To start the Flask application"
        // },
        // {
        //     "How can you enable debug mode in a Flask application?",
        //     "A) app.debug = True",
        //     "B) app.config['DEBUG'] = True",
        //     "C) FLASK_DEBUG = 1",
        //     "D) Both A and B",
        //     "Answer: D) Both A and B"
        // },
        // {
        //     "What is a view function in Flask and how do you create one?",
        //     "A) A function that returns Flask views, created with @view('/path')",
        //     "B) A function that returns HTML templates, created with @template('/path')",
        //     "C) A function that handles a route, created with @app.route('/path')",
        //     "D) A function that processes form data, created with @app.form('/path')",
        //     "Answer: C) A function that handles a route, created with @app.route('/path')"
        // },
        // {
        //     "How do you retrieve query parameters in Flask from a URL?",
        //     "A) request.query['parameter']",
        //     "B) request.args.get('parameter')",
        //     "C) request.get('parameter')",
        //     "D) request.params['parameter']",
        //     "Answer: B) request.args.get('parameter')"
        // },
        // {
        //     "What is the Jinja2 template engine and how is it used in Flask?",
        //     "A) A CSS framework for Flask",
        //     "B) A JavaScript engine for Flask applications",
        //     "C) A template engine for rendering HTML templates",
        //     "D) A database engine for Flask",
        //     "Answer: C) A template engine for rendering HTML templates"
        // },
        // {
        //     "How do you render a template file in a Flask view function?",
        //     "A) render_template('template.html')",
        //     "B) flask.render('template.html')",
        //     "C) app.render('template.html')",
        //     "D) template.render('template.html')",
        //     "Answer: A) render_template('template.html')"
        // },
        // {
        //     "What is the Flask-WTF extension and how do you use it to handle forms?",
        //     "A) A Flask extension for creating and managing forms",
        //     "B) A Flask tool for debugging",
        //     "C) A Flask extension for Windows Task Force",
        //     "D) A Flask widget toolkit",
        //     "Answer: A) A Flask extension for creating and managing forms"
        // },
        {
            "What is SASS and what does it stand for?",
            "A) Style And Syntax Sheet",
            "B) Syntactically Awesome Style Sheets",
            "C) Simple And Secure Styles",
            "D) Systematic Advanced Style Syntax",
            "Answer: B) Syntactically Awesome Style Sheets"
        },
        {
            "How do you declare a variable in SASS?",
            "A) var variableName: value;",
            "B) $variableName: value;",
            "C) @variableName: value;",
            "D) variableName = value;",
            "Answer: B) $variableName: value;"
        },
        {
            "What is the difference between SASS and SCSS?",
            "A) SCSS allows CSS syntax, SASS does not",
            "B) SASS is a newer version",
            "C) SCSS is more powerful",
            "D) No difference, just naming conventions",
            "Answer: A) SCSS allows CSS syntax, SASS does not"
        },
        {
            "How can you create a mixin in SASS?",
            "A) @mixin mixinName { ... }",
            "B) @function mixinName { ... }",
            "C) @include mixinName { ... }",
            "D) mixin mixinName { ... }",
            "Answer: A) @mixin mixinName { ... }"
        },
        {
            "What is the purpose of the @import directive in SASS?",
            "A) To import mixins",
            "B) To include CSS files",
            "C) To load SASS/SCSS files",
            "D) To import JavaScript files",
            "Answer: C) To load SASS/SCSS files"
        },
        {
            "How do you use nested rules in SASS?",
            "A) By using the @nest directive",
            "B) By placing selectors inside other selectors",
            "C) By separating selectors with a comma",
            "D) By using the & symbol",
            "Answer: B) By placing selectors inside other selectors"
        },
        {
            "What are partials in SASS and how do you use them?",
            "A) Variables that are partially used",
            "B) Incomplete CSS rules",
            "C) Small SASS files that can be included in other SASS files",
            "D) A method to partially import a file",
            "Answer: C) Small SASS files that can be included in other SASS files"
        },
        {
            "Explain the concept of inheritance in SASS with the @extend directive.",
            "A) Allowing one selector to inherit the styles of another",
            "B) Extending SASS with JavaScript",
            "C) Increasing the specificity of a selector",
            "D) Copying styles from one file to another",
            "Answer: A) Allowing one selector to inherit the styles of another"
        },
        {
            "How can you perform mathematical operations in SASS?",
            "A) Using JavaScript",
            "B) With special SASS math functions",
            "C) Directly in the CSS",
            "D) By using CSS variables",
            "Answer: B) With special SASS math functions"
        },
        {
            "What is the purpose of the @if directive in SASS?",
            "A) To check the type of a variable",
            "B) To import files conditionally",
            "C) To apply styles based on conditions",
            "D) To define SASS functions",
            "Answer: C) To apply styles based on conditions"
        }
    };
    
    
    
    
    

    // New endpoint for boolean questions
    @GetMapping("/boolean")
    public ResponseEntity<?> getBooleanMCQs() {
        var response = new Object() {
            Random rand = new Random();
            int rand1 = rand.nextInt(booleanQuestionsMCQ.length);
            public final String[] mcqs = booleanQuestionsMCQ[rand1];
        };
        return ResponseEntity.ok(response);
    }

    // New endpoint for sass questions
    @GetMapping("/sass")
    public ResponseEntity<?> getSassQuestions() {
        var response = new Object() {
            Random rand = new Random();
            int rand1 = rand.nextInt(sassQuestionsMCQ.length);
            public final String[] mcqs = sassQuestionsMCQ[rand1];
        };
        return ResponseEntity.ok(response);
    }

    // New endpoint for sass questions
    @GetMapping("/flask")
    public ResponseEntity<?> getFlaskQuestions() {
        var response = new Object() {
            Random rand = new Random();
            int rand1 = rand.nextInt(flaskSetupQuestionsMCQ.length);
            public final String[] mcqs = flaskSetupQuestionsMCQ[rand1];
        };
        return ResponseEntity.ok(response);
    }

    @GetMapping("/binary")
    public ResponseEntity<?> getBinaryQuestions() {
        var response = new Object() {
            Random rand = new Random();
            int rand1 = rand.nextInt(binaryQuestionsMCQ.length);
            public final String[] mcqs = binaryQuestionsMCQ[rand1];
        };
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allqs")
    public ResponseEntity<?> getQuestions() {
        var response = new Object() {
            int[] randIndex = new int[10];
            String[][] questions = new String[10][];
            Random rand = new Random();
            {
            for (int i=0; i<10; i++) {
                int rand1 = rand.nextInt(allQuestionsMCQs.length);
                randIndex[i] = rand1;
                questions[i] = allQuestionsMCQs[rand1];
            }
            }
            public final String[][] mcqs = questions;
        };
        return ResponseEntity.ok(response);
    }
}
