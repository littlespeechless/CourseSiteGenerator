package csg.transactions;

import csg.data.CourseSiteData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeTextField_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    String oldText;
    String newText; 
    TextField textField;
    
    public ChangeTextField_Transaction(CourseSiteData initData, String initNewText,String initOldText, TextField initField) {
        data = initData;
        textField = initField;
        oldText = initOldText;
        newText = initNewText;
    }

    @Override
    public void doTransaction() {
        textField.setText(newText);
    }

    @Override
    public void undoTransaction() {
        textField.setText(oldText);
    }
}
