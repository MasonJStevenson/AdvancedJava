package model;

/**
 * Interface for a basic calculator module.
 * @author MasonS
 * @version 1.0
 */
public interface Calculator
{
    /**
     * Invalid operation warning.
     */
    public static final String kInvalidOperation = 
            "Error: Invalid Operation.";
            
    /**
     * Invalid operator warning.
     */
    public static final String kInvalidOperator = 
            "Error: Invalid Operator.";
            
    /**
     * Number format warning.
     */
    public static final String kNumberFormat = 
            "Error: Invalid Number Format.";
            
    /**
     * Processes a string command.
     * 
     * @param command the command to process
     * @return Returns the result of the calculation.
     */
    public String processCommand(String command);
}
