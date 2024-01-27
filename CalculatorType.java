import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CalculatorType implements ItemListener {
    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        JRadioButton normalCalculator = new JRadioButton("Normal Calculator", false);
        JRadioButton scientificCalculator = new JRadioButton("Scientific Calculator", false);
        if(e.equals(normalCalculator)){

        }
//        else if (scientificCalculator) {

//        }
    }
}
