# Java Console Calculator

A simple **console-based calculator in Java** that evaluates mathematical expressions entered by the user.  
This project demonstrates **string parsing, operator precedence handling, and basic error handling**.

---

## Features

- Supports basic arithmetic operations: `+`, `-`, `*`, `/`.
- Correctly handles operator precedence:
  1. Division (`/`)
  2. Multiplication (`*`)
  3. Addition (`+`)
  4. Subtraction (`-`)
- Ignores whitespace in input expressions.
- Handles invalid characters and division by zero gracefully.
- Interactive console interface:
  - Continuously accepts expressions until the user types `"exit"`.
  - Shows results immediately after evaluation.

---

## Example Usage

=== Java Console Calculator ===
Type expressions like: 10+25*9-35/2
Type 'exit' to quit.

Enter expression: 10+25*2
Result: 60.0

Enter expression: 100/5-3
Result: 17.0

Enter expression: 50/2*3
Result: 75.0

Enter expression: exit
Exiting Calculator... Goodbye!


---

## How It Works

1. **Parsing Input**: Removes spaces and separates numbers and operators.
2. **Operator Precedence**: Processes division (`/`) first, then multiplication (`*`), then addition/subtraction (`+`/`-`).
3. **Evaluation**: Uses `ArrayList<Double>` for numbers and `ArrayList<Character>` for operators. Updates numbers after each operation until a single result remains.
4. **Error Handling**: Invalid characters or division by zero throw meaningful exceptions.

---

## Example Test Cases
______________________________________________
| Expression       | Expected Result         |
|------------------|-------------------------|
| 10+25*2          | 60.0                    |
| 100/5-3          | 17.0                    |
| 50/2*3           | 75.0                    |
| 2+3*4-6/2        | 10.0                    |
| 10/0             | Error: Division by zero |
----------------------------------------------

## Technologies Used

- Java 8+
- Core Java libraries: `java.util.Scanner`, `java.util.ArrayList`

---

## Future Improvements

- Add support for parentheses for complex expressions.  
- Include additional operators like exponentiation (`^`) and modulus (`%`).  
- Implement stack-based evaluation for better performance.  
- Add a GUI interface for improved user experience.
