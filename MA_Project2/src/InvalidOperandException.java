/**
 * Program Name: InvalidOperandException.java
 * Purpose: An exception class which is thrown if an operand cannot be entered (e.g., too many digits for integer)
 * Coder: Mohamad Arafat
 * Date: Aug 5, 2023
 */

public class InvalidOperandException extends RuntimeException {
    public InvalidOperandException(String message) {
        super(message);
    }
}

