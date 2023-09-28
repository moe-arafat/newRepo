/**
 * Program Name: IllegalOperationException.java
 * Purpose: An exception class which is thrown by Calculator if the selected operation cannot be performed on 
 * 			the current operands (e.g., divide-by-zero)
 * Coder: Mohamad Arafat
 * Date: Aug 5, 2023
 */

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String message) {
        super(message);
    }
}

