/**
 * Program Name: CalculatorApp.java
 * Purpose: A GUI class which uses a Calculator object and performs the I/O that feeds Calculator 
 * 			object performs calculator operations on that object.
 * Coder: Mohamad Arafat
 * Date: Aug 5, 2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
	
	//Declare the components and buttons arrays
	private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private JRadioButton decimalRadioButton;
    private JRadioButton octalRadioButton;
    private JRadioButton binaryRadioButton;
    private JRadioButton hexadecimalRadioButton;

    private JButton signToggleButton;
    private boolean isNegative;

    private double num1;
    private double num2;
    private char operation;

    private Calculator calculator;

    public CalculatorApp() {
    	//The window properties
        setTitle("Moe's Calculator");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        calculator = new Calculator();
        isNegative = false;

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.CENTER);
        displayField.setFont(new Font("Arial", Font.BOLD, 30));

        numberButtons = new JButton[10];
        
        //For loop that loops through the numbers array to add an action listener and add colors and adjust the font size
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 30));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(new Color(255, 99, 71));
        }

        operationButtons = new JButton[19];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        operationButtons[4] = new JButton("AND");
        operationButtons[5] = new JButton("OR");
        operationButtons[6] = new JButton("XOR");
        operationButtons[7] = new JButton("~");
        operationButtons[8] = new JButton("<<");
        operationButtons[9] = new JButton(">>");
        operationButtons[10] = new JButton("+/-");
        operationButtons[11] = new JButton(".");
        operationButtons[12] = new JButton("=");
        operationButtons[13] = new JButton("Clear");
        operationButtons[14] = new JButton("Back");
        operationButtons[15] = new JButton("%");
        operationButtons[16] = new JButton("NAND");
        operationButtons[17] = new JButton("NOR");
        operationButtons[18] = new JButton("NOT");
        
      //For loop that loops through the operations array to add an action listener and add colors and adjust the font size
        for (int x = 0; x < 19; x++) {
            operationButtons[x].setFont(new Font("Arial", Font.PLAIN, 20));
            operationButtons[x].addActionListener(this);
            operationButtons[x].setBackground(Color.WHITE);
        }
        operationButtons[13].setBackground(new Color(220, 20, 60));
        operationButtons[14].setBackground(new Color(255, 255, 102));
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(6, 5, 10, 10));

        //Add the buttons to the panel
        // First row
        
        buttonPanel.add(operationButtons[2]);
        buttonPanel.add(operationButtons[0]);
        buttonPanel.add(operationButtons[1]);
        buttonPanel.add(operationButtons[3]);
        buttonPanel.add(operationButtons[15]);
        
        
        //Second row
        
        buttonPanel.add(operationButtons[10]);
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operationButtons[7]);
        
         
        //Third row
        buttonPanel.add(operationButtons[13]);
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        
        
        //Forth row
        buttonPanel.add(operationButtons[8]);
        buttonPanel.add(operationButtons[14]);
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        
        
        //Fifth row
        buttonPanel.add(operationButtons[9]);
        buttonPanel.add(operationButtons[6]);
        buttonPanel.add(operationButtons[11]);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(operationButtons[12]);
        
        
        //Sixth row
        buttonPanel.add(operationButtons[5]);
        buttonPanel.add(operationButtons[16]);
        buttonPanel.add(operationButtons[17]);
        buttonPanel.add(operationButtons[18]);
        buttonPanel.add(operationButtons[4]);

        decimalRadioButton = new JRadioButton("Dec");
        decimalRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        octalRadioButton = new JRadioButton("Oct");
        octalRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        binaryRadioButton = new JRadioButton("Bin");
        binaryRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        hexadecimalRadioButton = new JRadioButton("Hex");
        hexadecimalRadioButton.setFont(new Font("Arial", Font.PLAIN, 20));

        ButtonGroup notationButtonGroup = new ButtonGroup();
        notationButtonGroup.add(decimalRadioButton);
        notationButtonGroup.add(octalRadioButton);
        notationButtonGroup.add(binaryRadioButton);
        notationButtonGroup.add(hexadecimalRadioButton);

        JPanel notationPanel = new JPanel(new GridLayout(1, 4));
        notationPanel.add(decimalRadioButton);
        notationPanel.add(octalRadioButton);
        notationPanel.add(binaryRadioButton);
        notationPanel.add(hexadecimalRadioButton);
        
        ActionListener notationListener = new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                updateDisplayConversion();
            }
        };
        
        decimalRadioButton.addActionListener(notationListener);
        octalRadioButton.addActionListener(notationListener);
        binaryRadioButton.addActionListener(notationListener);
        hexadecimalRadioButton.addActionListener(notationListener);

        mainPanel.add(displayField, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(notationPanel, BorderLayout.SOUTH);

        decimalRadioButton.setSelected(true);

        add(mainPanel);

        setVisible(true);
    }

    private JButton getButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(this);
        return button;
    }

    
    public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
        if (source instanceof JButton) {
            JButton clickedButton = (JButton) source;
            String buttonText = clickedButton.getText();

            if (Character.isDigit(buttonText.charAt(0))) {
                displayField.setText(displayField.getText() + buttonText);
            } else if (buttonText.equals("+") || buttonText.equals("-") ||
                    buttonText.equals("*") || buttonText.equals("/") ||
                    buttonText.equals("AND") || buttonText.equals("OR") ||
                    buttonText.equals("XOR") || buttonText.equals("<<") ||
                    buttonText.equals(">>") || buttonText.equals("~") ||
                    buttonText.equals("%") || buttonText.equals("NAND") ||
                    buttonText.equals("NOR")) {
                if (!displayField.getText().isEmpty()) {
                    num1 = Double.parseDouble(displayField.getText());
                    operation = buttonText.charAt(0);
                    displayField.setText("");
                }
            } 
            else if (buttonText.equals("%")) {
                if (!displayField.getText().isEmpty()) {
                    num1 = Double.parseDouble(displayField.getText());
                    operation = '%';
                    displayField.setText("");
                }
            }
            else if (buttonText.equals("=")) {
                try {
                    num2 = Double.parseDouble(displayField.getText());
                    double result;
                    if (operation == '+' || operation == '-' || operation == '*' || operation == '/' || operation == '%') {
                        result = calculator.performOperation(num1, num2, operation);
                    } else {
                        long longNum1 = (long) num1;
                        long longNum2 = (long) num2;
                        switch (operation) {
                            case 'A':
                                result = longNum1 & longNum2;
                                break;
                            case 'O':
                                result = longNum1 | longNum2;
                                break;
                            case 'X':
                                result = longNum1 ^ longNum2;
                                break;
                            case '<':
                                result = longNum1 << longNum2;
                                break;
                            case '>':
                                result = longNum1 >> longNum2;
                                break;
                            case 'L':
                                result = longNum1 << longNum2;
                                break;
                            case 'R':
                                result = longNum1 >>> longNum2;
                                break;
                            case '~':
                                result = ~longNum1;
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid operation");
                        }
                    }
                    displayResult(result);
                } catch (NumberFormatException ex) {
                    displayField.setText("Error");
                } catch (IllegalOperationException ex) {
                    displayField.setText("Error: " + ex.getMessage());
                }
            } else if (buttonText.equals("Clear")) {
                displayField.setText("");
            } else if (buttonText.equals("+/-")) {
                toggleOperandSign();
            } else if (buttonText.equals(".")) {
                if (!displayField.getText().contains(".")) {
                    displayField.setText(displayField.getText() + ".");
                }
            } else if (buttonText.equals("Back")) {
                if (!displayField.getText().isEmpty()) {
                    String currentText = displayField.getText();
                    displayField.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        }
    }
    
    //Update the value depending on which radio button was clicked after entering a number
    private void updateDisplayConversion() {
        String currentText = displayField.getText();
        if (!currentText.isEmpty()) {
            try {
                double value = Double.parseDouble(currentText);
                if (decimalRadioButton.isSelected()) {
                    displayField.setText(String.valueOf(value));
                } else if (octalRadioButton.isSelected()) {
                    displayField.setText(Long.toOctalString((long) value));
                } else if (binaryRadioButton.isSelected()) {
                    displayField.setText(Long.toBinaryString((long) value));
                } else if (hexadecimalRadioButton.isSelected()) {
                    displayField.setText(Long.toHexString((long) value));
                }
            } catch (NumberFormatException ex) {
                
            }
        }
    }
    
    private void toggleOperandSign() {
        if (!displayField.getText().isEmpty()) {
            double currentValue = Double.parseDouble(displayField.getText());
            currentValue = -currentValue;
            displayField.setText(String.valueOf(currentValue));
        }
    }

    //Display the result according to which radio button is clicked
    private void displayResult(double result) {
        if (decimalRadioButton.isSelected()) {
            displayField.setText(String.valueOf(result));
        } else if (octalRadioButton.isSelected()) {
            displayField.setText(Long.toOctalString((long) result));
        } else if (binaryRadioButton.isSelected()) {
            displayField.setText(Long.toBinaryString((long) result));
        } else if (hexadecimalRadioButton.isSelected()) {
            displayField.setText(Long.toHexString((long) result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp());
    }
}
