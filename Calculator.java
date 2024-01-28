import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator extends CalculatorType implements ActionListener {
    JFrame frame, warningFrame, historicFrame;
    File file = new File("Calculator.txt");
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, multiButton, divButton, negButton;
    JButton decButton, equButton, delButton, clrButton, backButton;
    JPanel panel, hPanel;
    private JRadioButton normalCalculator, scientificCalculator;
    private CalculatorType calculatorType;
    Font myFont = new Font("Console", Font.ITALIC, 25);
    double num1 = 0, num2 = 0.0, result = 0.0;
    char operator;

    JTextArea copy = new JTextArea("ROI_Group @ ROITech - 2023");
    JTextArea warningText = new JTextArea("        !! Invalid Operation !!\nYou can't divide a number By 0");
    JButton warningOKButton = new JButton(" OK ");
    JTextArea historicTextArea;
    JButton historicButton = new JButton("Historic");

    /**
     * Calculator
     */
    Calculator() {
        {
            if (!file.exists()) {
                try {
                    boolean b = file.createNewFile();
                    System.out.println(b);
                } catch (IOException e) {
                    System.out.println("Error!!\n" + e.getMessage());
                }
            }
        }
        frame = new JFrame("CALCULATOR 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);

        textField = new JTextField("0");
        textField.setBounds(30, 30, 350, 40);
        textField.setFont(myFont);
        textField.setBackground(Color.lightGray);
//
//
//        String regexText = "([0-9])+\\.([0-9])+";
//        Pattern pattern = Pattern.compile(regexText);
//        String text = String.valueOf(textField.getText());
//        Matcher matcher = pattern.matcher(text);
//
//        if (matcher.find()) {
//         textField.setEditable(true);
//        }else {
        textField.setEditable(false);
//        }
        /**
         *
         */
        textField.addActionListener(this);


        addButton = new JButton("+");
        subButton = new JButton("-");
        multiButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = multiButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

//
//
        historicButton.setBounds(292, 80, 86, 25);
        historicButton.setBackground(Color.LIGHT_GRAY);
        historicButton.setForeground(Color.BLUE);
        historicButton.setFont(new Font("Lucida", Font.ITALIC, 15));

//
//
        normalCalculator = new JRadioButton();
        scientificCalculator = new JRadioButton();
        calculatorType = new CalculatorType();
        normalCalculator = new JRadioButton("Normal Calculator", true);
        scientificCalculator = new JRadioButton("Scientific Calculator", false);
        normalCalculator.setBounds(30, 75, 150, 20);
        scientificCalculator.setBounds(30, 95, 150, 20);

//
//
        copy.setFont(new Font("Console", Font.ITALIC, 10));
        copy.setBounds(145, 540, 140, 15);
        copy.setBackground(Color.lightGray);


        negButton.setBounds(30, 480, 95, 45);


        delButton.setBounds(140, 480, 120, 45);
        clrButton.setBounds(272, 480, 108, 45);

        panel = new JPanel();
        panel.setBounds(30, 120, 350, 345);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
//        panel.setBackground(Color.BLUE);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);


        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
//
//
        normalCalculator.addItemListener(calculatorType);
        scientificCalculator.addItemListener(calculatorType);
//
        historicButton.addActionListener(this);
        frame.add(historicButton);

        frame.add(normalCalculator);
        frame.add(scientificCalculator);
//        frame.add(option);
        frame.add(copy);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {


        if (textField.getText().equals("0")) {
            textField.setText("");
        }
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }

        }

        if (e.getSource() == historicButton) {
            frame.setVisible(false);
            historicFrame = new JFrame("Históric");
            historicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            historicFrame.setSize(420, 600);
            historicFrame.setLayout(null);

            JTextArea title = new JTextArea("                   Históric               ");
            title.setFont(myFont);
            title.setBounds(20, 20, 360, 35);

            backButton = new JButton(">");
            backButton.setForeground(Color.GREEN);
            backButton.setBackground(Color.WHITE);
            backButton.setBounds(330, 63, 50, 30);
            backButton.setFont(myFont);
            backButton.setToolTipText("Back to the Calculator");

            Container container = new Container();
            JPanel jpPanel = new JPanel ();
            container.add(jpPanel);

            GridBagLayout gridBagLayout = new GridBagLayout();
            jpPanel.setLayout(gridBagLayout);

            JList<String> list = new JList<String>();
            list.setListData(readData());




//            JTextArea history = readData();
            hPanel = new JPanel();
            hPanel.setBounds(20, 100, 360, 440);
            hPanel.setBackground(Color.LIGHT_GRAY);




            historicFrame.add(backButton);
//            historicFrame.add(history);
            historicFrame.add(hPanel);
            historicFrame.add(title);
            historicFrame.setVisible(true);
        }

        if (e.getSource() == decButton) {
            if (!Objects.equals(textField.getText(), "")) {
                textField.setText(textField.getText().concat("."));
            } else {
                textField.setText(textField.getText().concat("0."));

            }
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        } else if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        } else if (e.getSource() == multiButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        } else if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            isZeroOrNot();
//            if (Objects.equals(textField.getText(), " ") || Objects.equals(textField.getText(), "0") || Objects.equals(textField.getText(), "")) {
//                num2 = 0;
//            } else {
//                num2 = Double.parseDouble(textField.getText());
//            }
            switch (operator) {
                case '+':
                    isZeroOrNot();
                    result = num1 + num2;
                    break;
                case '-':
                    isZeroOrNot();
                    result = num1 - num2;
                    break;
                case '*':
                    isZeroOrNot();
                    result = num1 * num2;
                    break;
                case '/':
                    isZeroOrNot();
                    if (num2 == 0) {
                        invalidOperation();
                        break;
                    } else {
                        result = num1 / num2;
                        break;
                    }
            }
            isIntegerOrDouble(result, (byte) 1);
            ////////////////////////////////////////////////////////////////
            String operation = num1 + " " + operator + " " + num2 + " = " + result;
            writeData(operation);
            /////////////////////////////////////////////////////////////////
            num1 = result;
            result = 0;
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }
        if (e.getSource() == delButton) {
            String textNum = textField.getText();
            textField.setText("");
            for (int i = 0; i < textNum.length() - 1; i++) {
                textField.setText(textField.getText() + textNum.charAt(i));

            }
        }

        if (e.getSource() == negButton && !Objects.equals(textField.getText(), "")) {
            double value = Double.parseDouble(textField.getText());
            //we need to know if the value is null or has a number
            isIntegerOrDouble(value, (byte) -1);
        }

        if (e.getSource() == warningOKButton) {
            warningFrame.setVisible(false);
        }


    }


    private void isZeroOrNot() {
        if (Objects.equals(textField.getText(), " ") || Objects.equals(textField.getText(), "0") || Objects.equals(textField.getText(), "")) {
            num2 = 0;
        } else {
            num2 = Double.parseDouble(textField.getText());
        }
    }

    private void isIntegerOrDouble(double result, byte temp) {
        /*
         * The var 'regex' will return one or more "numbers" that will end with ".0"
         * The pattern compile the String 'regex'
         * It'll be used after typing the "=" sign in the calculator.
         */
        String regex = "([0-9])+\\.0$";
        Pattern pattern = Pattern.compile(regex);
        /*
         * <h4> Regex && Matcher <h4/>
         * This block of code will help you to match the result you
         * obtained with the pattern.
         */
        String text = String.valueOf(result);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            int res = (int) result * temp;
            textField.setText(String.valueOf(res));
        } else {
            textField.setText(String.valueOf(result * temp));
        }
    }

    private void invalidOperation() {
        warningFrame = new JFrame("!! ERROR !!");
        warningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        warningFrame.setSize(400, 300);
        warningFrame.setBackground(Color.lightGray);
        warningFrame.setLayout(null);

        warningText.setBounds(20, 80, 340, 60);
        warningText.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        warningText.setBackground(Color.lightGray);
        warningText.setForeground(Color.red);

        warningOKButton.setBounds(150, 180, 90, 35);
        warningText.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        warningOKButton.setBackground(Color.red);
        warningOKButton.addActionListener(this);

        warningFrame.add(warningOKButton);
        warningFrame.add(warningText);
        warningFrame.setVisible(true);
    }

    private void writeData(String data) {
        try (FileWriter fileWriter = new FileWriter(file, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String[] readData() {
        String [] data = new String[20000];
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            int i = 1;
            while (( line = bufferedReader.readLine()) != null) {
                 data[i] = line;
                 i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
//        historicTextArea = new JTextArea(data);
//        historicTextArea.setLineWrap(true);
    return data;
    }


}

