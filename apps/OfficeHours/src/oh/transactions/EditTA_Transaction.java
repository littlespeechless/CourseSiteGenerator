package oh.transactions;

import jtps.jTPS_Transaction;
import oh.data.OfficeHoursData;
import oh.data.TeachingAssistantPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class EditTA_Transaction implements jTPS_Transaction {
    OfficeHoursData data;
    TeachingAssistantPrototype ta;
    String newNameString;
    String newEmailString;
    String newTypeString;
    String oldNameString;
    String oldEmailString;
    String oldTyString;
    
    public EditTA_Transaction(OfficeHoursData initData, TeachingAssistantPrototype initTA,String name, String email, String type) {
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
