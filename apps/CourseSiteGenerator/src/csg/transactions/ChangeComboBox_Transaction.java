package csg.transactions;

import static csg.CourseSitePropertyType.SITE_SUBJECT_COMBO_BOX;
import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import djf.modules.AppGUIModule;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeComboBox_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    String oldString;
    String newString; 
    ComboBox comboBox;
    
    public ChangeComboBox_Transaction(CourseSiteData initData, String initOldString,String initNewsString, ComboBox initBox) {
        data = initData;
        comboBox = initBox;
        oldString = initOldString;
        newString = initNewsString;
    }

    @Override
    public void doTransaction() {
        comboBox.setValue(newString);
    }

    @Override
    public void undoTransaction() {
        comboBox.setValue(oldString);
    }
}
