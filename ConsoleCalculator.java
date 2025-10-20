import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleCalculator {

    public static double Expression(String expression) {
        expression = expression.replaceAll("\\s+", "");

        ArrayList<Double> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        StringBuilder num = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                num.append(ch);
            } else if ("+-*/".indexOf(ch) != -1) {
                numbers.add(Double.parseDouble(num.toString()));
                num.setLength(0);
                operators.add(ch);
            } else {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }
        if (num.length() > 0)
            numbers.add(Double.parseDouble(num.toString()));

        ArithmeticOperatoin(numbers, operators, '/');

        ArithmeticOperatoin(numbers, operators, '*');

        ArithmeticOperatoin(numbers, operators, '+');

        ArithmeticOperatoin(numbers, operators, '-');

        return numbers.get(0);
    }

    private static void ArithmeticOperatoin(ArrayList<Double> numbers, ArrayList<Character> operators, char targetOp) {
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == targetOp) {
                double a = numbers.get(i);
                double b = numbers.get(i + 1);
                double result;

                switch (targetOp) {
                    case '/':
                        if (b == 0)
                            throw new ArithmeticException("Division by zero");
                        result = a / b;
                        break;
                    case '*':
                        result = a * b;
                        break;
                    case '+':
                        result = a + b;
                        break;
                    case '-':
                        result = a - b;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + targetOp);
                }

                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Java Console Calculator ===");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            System.out.print("Enter expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Shutting down the calculator… See you later!");
                break;
            }

            try {
                double result = Expression(input);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Wrong input: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
