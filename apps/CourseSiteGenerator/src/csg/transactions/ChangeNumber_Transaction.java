package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeNumber_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    String oldNumber;
    String newNumber; 
    ComboBox comboBox;
    
    public ChangeNumber_Transaction(CourseSiteData initData, String initNumber,String initNewNumber, ComboBox initBox) {
        data = initData;
        comboBox = initBox;
        newNumber = initNewNumber;
        oldNumber = initNumber;
    }

    @Override
    public void doTransaction() {
        comboBox.setValue(newNumber);
        data.addNumber(newNumber);
    }

    @Override
    public void undoTransaction() {
        comboBox.setValue(oldNumber);
    }
}
