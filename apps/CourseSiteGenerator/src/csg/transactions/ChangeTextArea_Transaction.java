package csg.transactions;

import csg.data.CourseSiteData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeTextArea_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    String oldText;
    String newText; 
    TextArea textArea;
    public ChangeTextArea_Transaction(CourseSiteData initData, String initNewText,String initOldText, TextArea initTextArea) {
        data = initData;
        textArea = initTextArea;
        oldText = initOldText;
        newText = initNewText;
    }

    @Override
    public void doTransaction() {
        textArea.setText(newText);
    }

    @Override
    public void undoTransaction() {
        textArea.setText(oldText);
    }
}
