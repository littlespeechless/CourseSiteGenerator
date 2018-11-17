package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import java.awt.Checkbox;
import javafx.scene.control.CheckBox;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeCheckBox_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    CheckBox checkbox;
    boolean isChecked;
    
    public ChangeCheckBox_Transaction(CourseSiteData initData, CheckBox initBox, boolean initCheck) {
        data = initData;
        checkbox = initBox;
        isChecked = initCheck;
    }

    @Override
    public void doTransaction() {
        checkbox.setSelected(isChecked);
    }

    @Override
    public void undoTransaction() {
        checkbox.setSelected(!isChecked);
    }
}
