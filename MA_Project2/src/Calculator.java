/**
 * Program Name: Calculator.java
 * Purpose: A class to be used to handle the computation
 * Coder: Mohamad Arafat
 * Date: Aug 5, 2023
 */

public class Calculator {
	public double performOperation(double num1, double num2, char operation) throws IllegalOperationException {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new IllegalOperationException("Division by zero");
                }
                return num1 / num2;
            case '%':
                return num1 % num2; 
            
            default:
                throw new IllegalOperationException("Invalid operation");
        }
    }
}