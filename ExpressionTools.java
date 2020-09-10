/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolec;

/**
 *
 * @author zee
 */


import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * @author Monica Martinez
 * @version November 16,2014
 * 
 * Expression Tools class creates various methods for 
 * evaluating post-fix expressions and
 * converting infix to post-fix expressions.
 *
 */
public class ExpressionTools {
	
	//Data-fields for storing math expressions
	StringBuilder infixExp = new StringBuilder();
	StringBuilder postfixExp = new StringBuilder();
	
	static String[] operands = new String[]{"*","/","-","+"};
	
//UPDATED: check input validity ---> "+ 2 2" 	
	/**
	 * Checks whether input is a valid infix expression. 
	 * 
	 * @param infixExp
	 * @return true if does not start or end with an operand
	 * @throws Exception
	 */
	public static boolean isValidInfix(String infixExp) throws Exception {
		for(String s: operands) 
			if(infixExp.startsWith(s) || infixExp.endsWith(s))
				throw new Exception("INVALID INPUT");
		return true;
	}

	/**
	 * Determines whether the string value is an operator.
	 * @param value
	 * @return false if value is not an operator.
	 */
	private static boolean isOperator(String value) {
		
		if (value.equals("*") || value.equals("/") 
				|| value.equals("+") || value.equals("-"))
			return true;
		return false;
	}
	
    /**
     * Determines an operator's precedence 
     * @param operator
     * @return priority value based on precedence ranking
     */
    private static int precedence(String operator) {
        int priority = 0;

        if (operator.equals("+") || operator.equals("-"))
        	priority = 1;
        else if (operator.equals("*") || operator.equals("/"))
        	priority = 2;
        
        return priority;
    }
    
    /**
     * Determines whether string is an operandpro
     * @param string
     * @return true if string is a number
     */
    private static boolean isNumber(String string) {  
    	  
    	char num = string.charAt(0);
    	return Character.isDigit(num);
    } 
    
	/**
	 * Evaluates Post-fix Expressions.
	 * Based on the Dale/Joyce/Weems implementation.
	 * @param convExp
	 * @return result
	 * @throws PostFixException
	 */
	public static Integer postFixEvaluator(String convExp) throws PostFixException {

			Stack<Integer> stack = new Stack<Integer>();

			int value;
			String operator;

			int operand1;
			int operand2;

			int result = 0;

			Scanner tokenizer = new Scanner(convExp);

			while (tokenizer.hasNext()) {
				if (tokenizer.hasNextInt()) {
					// Process operand.
					value = tokenizer.nextInt();

					stack.push(value);
				} else {
					// Process operator.
					operator = tokenizer.next();

					// Obtain second operand from stack.
					if (stack.isEmpty()) {
						tokenizer.close();
						throw new PostFixException("Not Enough Operands - Stack Underflow");
					}
					operand2 = stack.peek();
					stack.pop();

					// Obtain first operand from stack.
					if (stack.isEmpty()) {
						tokenizer.close();
						throw new PostFixException("Not Enough Operands - Stack Underflow");
					}
					operand1 = stack.peek();
					stack.pop();

					// Perform operation.
					if (operator.equals("/"))
						result = operand1 / operand2;
					else if (operator.equals("*"))
						result = operand1 * operand2;
					else if (operator.equals("+"))
						result = operand1 + operand2;
					else if (operator.equals("-"))
						result = operand1 - operand2;
					else {
						tokenizer.close();
						throw new PostFixException("Illegal Symbol: " + operator);
					}

					// Push result of operation onto stack.
					stack.push(result);
				}
			}
			tokenizer.close();

			// Obtain final result from stack.
			if (stack.isEmpty())
				throw new PostFixException("Not Enough Operands - Stack Underflow");
			result = stack.peek();
			stack.pop();

			// Stack should now be empty.
			if (!stack.isEmpty())
				throw new PostFixException("Too Many Operands - Operands Left Over");

			// Return the final.
			return result;
		}
    
	/**
	 * Converts infix expressions into post-fix expressions.
	 * 
	 * @param expression
	 * @return post-fix expression
	 * @throws PostFixException
	 */
	public static String postFixConverter(String expression) throws PostFixException {

		Scanner tokenizer = new Scanner(expression);
		LLStack<String> stack = new LLStack<String>();
		StringBuilder postfixExp = new StringBuilder();
		
		String token;
		String curToken;
		
		while (tokenizer.hasNext()) {

			token = tokenizer.next();

			if (isNumber(token)) 					
				postfixExp.append(token).append(" ");	
			else if (token.equals( "(" )) 
				stack.push(token);	
			else if(isOperator(token)){
				
				int tokenPrecedence = precedence(token);
				
				while (!stack.isEmpty() && precedence(stack.peek()) >= tokenPrecedence) {
					curToken = stack.peek();
					postfixExp.append(curToken).append(" ");
					stack.pop();
				}
				stack.push(token);	
			}
			else if (token.equals( ")" )) {
				while (!stack.isEmpty()){
					curToken = stack.peek();
					if(!curToken.equals("(")){
						stack.pop();
						postfixExp.append(curToken).append(" ");
					}
					else {
						stack.pop();
						break;
					}
				}
			}
			else {
				tokenizer.close();
				throw new PostFixException("Not Valid Operands/Operators");
			}
		}		
		while (!stack.isEmpty()) {
			token = stack.peek();
			postfixExp.append(token +" ");
			stack.pop();
		}
		tokenizer.close();
		// Stack should now be empty.
		if (!stack.isEmpty())
			throw new PostFixException("Too Many Operators - Operators Left Over");
		          
		return postfixExp.toString();		
	}
}
	

