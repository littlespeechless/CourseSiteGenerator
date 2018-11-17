package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeSubject_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    String oldSubject;
    String newSubject; 
    ComboBox comboBox;
    
    public ChangeSubject_Transaction(CourseSiteData initData, String initSubject,String initNewSubject, ComboBox initBox) {
        data = initData;
        comboBox = initBox;
        newSubject = initNewSubject;
        oldSubject = initSubject;
    }

    @Override
    public void doTransaction() {
        comboBox.setValue(newSubject);
        data.addSubject(newSubject);
    }

    @Override
    public void undoTransaction() {
        comboBox.setValue(oldSubject);
    }
}
