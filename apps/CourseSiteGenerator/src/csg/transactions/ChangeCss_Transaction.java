package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeCss_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    ComboBox cssBox;
    String oldValue;
    String newValue;
    
    public ChangeCss_Transaction(String initOldCss, String initNewCss, ComboBox initBox) {
        oldValue = initOldCss;
        newValue = initNewCss;
        cssBox = initBox;
    }

    @Override
    public void doTransaction() {
        cssBox.setValue(newValue);
    }

    @Override
    public void undoTransaction() {
       cssBox.setValue(oldValue);
    }
}
