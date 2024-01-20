import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, multiButton, divButton, negButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;
    String[] types = new String[]{"Normal Calculator", "Scientific Calculator"};
    JList<String> option = new JList<>(types);
    Font myFont = new Font("Console", Font.ITALIC, 25);
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    JTextArea copy = new JTextArea("ROI_Group @ ROITech - 2023");


    /**
     * Calculator
     */
    Calculator() {
        frame = new JFrame("CALCULATOR 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 30, 350, 40);
        textField.setFont(myFont);
        textField.setBackground(Color.lightGray);
        textField.setEditable(false);


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

        option.setBounds(30, 75, 120, 40);
        option.setVisibleRowCount(2);
        option.setBackground(Color.lightGray);
        option.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//
//
        copy.setFont(myFont);
        copy.setBounds(100, 560, 70, 30);


        negButton.setBounds(30, 480, 95, 45);


        delButton.setBounds(140, 480, 120, 45);
        clrButton.setBounds(270, 480, 110, 45);

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
        frame.add(option);
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
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
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
//        if (!Objects.equals(textField.getText(), "")) {
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());


            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        operacaoImpossivel();

                    } else {
                        result = num1 / num2;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
            result = 0;
        }
//        }
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
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }


    }

    private void operacaoImpossivel() {
        JOptionPane.showMessageDialog(null, "Operacao inválida !");
    }
}

