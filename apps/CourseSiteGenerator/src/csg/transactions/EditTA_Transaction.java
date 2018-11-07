package csg.transactions;

import jtps.jTPS_Transaction;
import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class EditTA_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    TeachingAssistantPrototype ta;
    String newNameString;
    String newEmailString;
    String newTypeString;
    String oldNameString;
    String oldEmailString;
    String oldTyString;
    
    public EditTA_Transaction(CourseSiteData initData, TeachingAssistantPrototype initTA,String name, String email, String type) {
        data = initData;
        ta = initTA;
        newNameString = name;
        newEmailString = email;
        newTypeString = type;
        oldNameString = ta.getName();
        oldEmailString = ta.getEmail();
        oldTyString=ta.getType();
    }

    @Override
    public void doTransaction() {
        data.editTA(ta,newNameString,newEmailString,newTypeString);    
    }

    @Override
    public void undoTransaction() {
        data.editTA(ta,oldNameString,oldEmailString,oldTyString);
    }
}
