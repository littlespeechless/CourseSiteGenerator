package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class RemvTA_Transaction implements jTPS_Transaction {
    CourseSiteData data;
    TeachingAssistantPrototype ta;
    
    public RemvTA_Transaction(CourseSiteData initData, TeachingAssistantPrototype initTA) {
        data = initData;
        ta = initTA;
    }

    @Override
    public void doTransaction() {
        data.cutTA(ta);        
    }

    @Override
    public void undoTransaction() {
        data.cutTARestore(ta);
    }
}
