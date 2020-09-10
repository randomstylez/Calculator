
package consolec;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Calculator {
	
   public static void main(String[] args) {
     
	   EventQueue.invokeLater(new Runnable() {
            public void run() {
               CalculatorFrame frame = new CalculatorFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
	   });
   }
}

/**
 	* Creates a frame with a calculator panel.
 */
	class CalculatorFrame extends JFrame {
		public CalculatorFrame() {
			setTitle("Infix to Postfix Calculator");
			CalculatorPanel panel = new CalculatorPanel();
			add(panel);
			pack();
		}
	}

/**
 	* Creates a panel with calculator buttons and input and result Display.
 */
	class CalculatorPanel extends JPanel implements ActionListener {

		//Window Data-fields
		private JButton display;
		private JPanel panel;
		
		//Calculation Data-fields
		private int result;
		private String expression;

		public CalculatorPanel() {
			
			setLayout(new BorderLayout());
	
			// add the display

			display = new JButton();
			display.setEnabled(false);
			add(display, BorderLayout.NORTH);

			
			// add the buttons in a 6 x 4 grid

			panel = new JPanel();
			panel.setLayout(new GridLayout(5, 4));

			createButton("7");
			createButton("8");
			createButton("9");
			createButton("/");

			createButton("4");
			createButton("5");
			createButton("6");
			createButton("*");

			createButton("1");
			createButton("2");
			createButton("3");
			createButton("-");

			createButton("0");
			createButton("Convert");
			createButton("=");
			createButton("+");
			
			createButton("Delete");
			createButton("Clear");
		
			add(panel, BorderLayout.CENTER);
		}


		/**
		 * Creates buttons with actions and adds them to the panel.
		 * @param label
		 */
		private void createButton(String label) {		
			JButton button = new JButton(label);
			button.addActionListener(this);
			panel.add(button);
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			
			String input = event.getActionCommand();
			String tmpExp;
			
			//Evaluates Infix to Post-fix text input based on the ExpressionTools Class implementation
			if (input.equals("=")) {

				try 
				{
					result = ExpressionTools.postFixEvaluator(display.getText());
					display.setText(" " + result + " ");
				} 
				catch (PostFixException e) 
				{
					display.setText("INVALID");
				}			
			}
			//Converts Infix to Post-fix text input based on the ExpressionTools Class implementation
			else if (input.equals("Convert")) {
				
//UPDATED: checks for invalid infix expression
				try 
				{
					ExpressionTools.isValidInfix(display.getText());
					expression = ExpressionTools.postFixConverter(display.getText()) ;
					display.setText(expression);
				} 
				catch (Exception e)
				{
					display.setText("INVALID");
				}
			}
			//Clears the text input
			else if (input.equals("Clear"))
				display.setText("");
		
			//Allows user to delete last input
			else if (input.equals("Delete")) {
				tmpExp = display.getText();
				display.setText(tmpExp.substring(0, tmpExp.length()-1));
			}
			
//UPDATED: creates space delineated expression and resets if invalid
			else {
				if (display.getText().equals("INVALID"))
					display.setText("");	
				
				String plus = "+", minus = "-", div = "/", mult = "*";
				
				if (input.equals(plus) || input.equals(minus) || input.equals(div)||input.equals(mult)) {
					display.setText(display.getText() + " " + input+ " ");
					return;
				}
				
				display.setText(display.getText() + input);		
			}
		}		
	}
