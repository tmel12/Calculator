package org.example;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Updated regex pattern to correctly capture operators and operands.
        Pattern pattern = Pattern.compile("\\s*(\\d+)\\s*([+\\-*/%])\\s*(\\d+)\\s*");
        Pattern quitPattern = Pattern.compile("^\\s*:q\\s*$");

        System.out.println("Gebe deine Zahlen ein (oder :q zum beenden):");

        while (true) {
            System.out.print("Ausdruck eingeben: ");
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            Matcher quitMatcher = quitPattern.matcher(input);

            if (quitMatcher.matches()) {
                System.out.println("Taschenrechner wird gestoppt.");
                break; // Stops the calculator if :q is entered
            } else if (matcher.matches()) {
                double firstOperand = Double.parseDouble(matcher.group(1));
                char operator = matcher.group(2).charAt(0);
                double secondOperand = Double.parseDouble(matcher.group(3));
                double result;

                switch (operator) {
                    case '+':
                        result = firstOperand + secondOperand;
                        break;
                    case '-':
                        result = firstOperand - secondOperand;
                        break;
                    case '*': // Changed from 'x' to '*'
                        result = firstOperand * secondOperand;
                        break;
                    case '/':
                        if (secondOperand == 0) {
                            System.err.println("Fehler: Division durch Null.");
                            continue; // Skip the rest of the loop if division by zero is attempted
                        }
                        result = firstOperand / secondOperand;
                        break;
                    case '%':
                        result = firstOperand % secondOperand;
                        break;
                    default:
                        System.err.println("Unbekannter Operator: " + operator);
                        continue;
                }
                System.out.println(input + " = " + result);
            } else {
                System.err.println("Ungültiger Ausdruck. Bitte versuche es erneut.");
            }
        }
        scanner.close(); // It's good practice to close the scanner when you're done with it.
    }
}
