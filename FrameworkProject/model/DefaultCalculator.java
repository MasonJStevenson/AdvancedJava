package model;

/**
 * Basic Calculator. Processes commands of type (num) (operator) (num)
 * 
 * @author MasonS
 * @version 1.0
 */
public class DefaultCalculator implements Calculator
{
    private static final int kCommandLen = 3;
    
    /**
     * Processes a command of type (num) operator (num).
     * 
     * @param command the command to process
     * @return Returns the result of the calculation.
     */
    public String processCommand(String command)
    {
        String[] commands = command.split(" ");
        
        
        //check for valid command length
        if (commands.length == kCommandLen)
        {
            return doCommand(commands[0], commands[1], commands[2]);
        }
        
        //otherwise, do nothing
        else
        {
            return kInvalidOperation;
        }
    }
    
    private boolean isFloatingPoint(String str)
    {
        return str.matches("[-+]?\\d+(\\.\\d+)?");
    }
    
    private boolean isInteger(String str)
    {
        return str.matches("[-+]?\\d+");
    }
    
    private String doCommand(String left, String op, String right)
    {
        String result = "";
        int iLeft;
        int iRight;
        double dLeft;
        double dRight;
        String type = "";
        
        //check for int
        if (isInteger(left) && isInteger(right))
        {
            try
            {
                iLeft = Integer.parseInt(left);
                iRight = Integer.parseInt(right);
                type = "int";
                return "Result: " + operate(iLeft, op, iRight);
            }
            
            catch (Exception e)
            {
                return kNumberFormat;
            }
        }
        
        //check for floating point
        else if (isFloatingPoint(left) || isFloatingPoint(right))
        {
            try
            {
                dLeft = Double.parseDouble(left);
                dRight = Double.parseDouble(right);
                type = "double";
                return "Result: " + operate(dLeft, op, dRight);
            }
            
            catch (Exception e)
            {
                return kNumberFormat;
            }
        }
        
        //neither int nor floating point
        else
        {
            return "";
        } 
    }
    
    private String operate(int left, String op, int right)
    {
        String result;
        
        //check operator
        switch(op.charAt(0))
        {
            case '+':
                result = left + right + "";
                break;
            case '-':
                result = left - right + "";
                break;
            case '/':
                
                //check for remainder
                if (left % right == 0)
                {
                    result = left / right + "";
                }
                
                //cast to double
                else
                {
                    result = (double) left / (double) right + "";
                }
                
                break;
            case '*':
                result = left * right + "";
                break;
            default:
                result = kInvalidOperator;
                break;
        }
        
        return result;
    }
    
    private String operate(double left, String op, double right)
    {
        String result;
        
        //check operator
        switch(op.charAt(0))
        {
            case '+':
                result = left + right + "";
                break;
            case '-':
                result = left - right + "";
                break;
            case '/':
                result = left / right + "";
                break;
            case '*':
                result = left * right + "";
                break;
            default:
                result = kInvalidOperator;
                break;
        }
        
        return result;
    }
}
