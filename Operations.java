import javax.swing.*;

public class Operations {
    public double add(double num1, double num2){
        return num1+num2;
    }
    public double subtraction(double num1, double num2){
        return num1-num2;
            }
    public double division(double num1, double num2){
        if(num2 ==0){
            invalidOperation();
         return 0;
        }else {
            return num1 / num2;
        }
    }
    public double multiplication(double num1, double num2){
        return num1*num2;
    }

    public void invalidOperation() {
        JOptionPane.showMessageDialog(null, "    SORRY !!    \nYou cannot divide a number with 0.\nBut you can Divide 0 with a number");
    }
}
